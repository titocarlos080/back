def cargar(request):
    if request.method == "POST":
        # Obtener el archivo subido
        csv_file = request.FILES.get("csv_file")

        # Validar que el archivo tenga extensión .csv
        if not csv_file.name.endswith(".csv"):
            return HttpResponse("El archivo debe tener formato CSV.")

        # Leer y procesar el archivo CSV
        try:
            data = csv_file.read().decode("utf-8").splitlines()  # Decodificar el archivo
            reader = csv.DictReader(data)

            personas = []
            for row in reader:
                if "id" in row:
                    del row["id"]
                # Convertir la fecha al formato esperado por Django
                fecha_nac = datetime.strptime(row["fnac"], "%m/%d/%Y").date()
               
                # Normalizar el campo "telefono" (dejar solo los primeros 8 dígitos válidos)
                telefono = "".join(filter(str.isdigit, row["telefono"]))[:8]

                # Normalizar el campo "sexo"
                sexo = row["gender"].lower()
                if sexo == "male":
                    sexo = "masculino"
                elif sexo == "female":
                    sexo = "femenino"
                else:
                    sexo = "masculino"

                # Crear instancia de Persona
                persona = Persona(
                    nombre=row["nombre"],
                    apellidos=row["apellidos"],
                    sexo=sexo,
                    fnac=fecha_nac,
                    telefono=telefono,
                    rol=row["rol"],
                    #especialidad=row["especialidad"] if row["especialidad"] else None,
                    especialidad=None,
                )
                # Imprimir la instancia de Persona antes de agregarla
                print(f"Persona creada: {persona._dict_}")
                personas.append(persona)

            # Guardar todas las personas en la base de datos
            #print( 'datos: ',personas)
            Persona.objects.bulk_create(personas)

            return HttpResponse("Datos cargados exitosamente.")
        except Exception as e:
            return HttpResponse(f"Error al procesar el archivo: {str(e)}")

    return render(request, "cargar.html")


def cargar_tests(request):
    if request.method == "POST":
        # Obtener el archivo subido
        csv_file = request.FILES.get("csv_file")

        # Validar que el archivo tenga extensión .csv
        if not csv_file.name.endswith(".csv"):
            return HttpResponse("El archivo debe tener formato CSV.")

        # Leer y procesar el archivo CSV
        try:
            data = csv_file.read().decode("utf-8").splitlines()  # Decodificar el archivo
            reader = csv.DictReader(data)

            tests = []
            resultados = []
            for row in reader:
                # Convertir la fecha al formato esperado por Django
                fecha_prueba = datetime.strptime(row["fecha"], "%m/%d/%Y").date()

                if "covid" in row["nombre"].lower():
                    dias_entrega = random.randint(1, 2)
                elif "paternidad" in row["nombre"].lower():
                    dias_entrega = random.randint(5, 10)
                elif "hemograma" in row["nombre"].lower():
                    dias_entrega = random.randint(1, 3)
                elif "influenza" in row["nombre"].lower():
                    dias_entrega = random.randint(2, 4)
                elif "alergia" in row["nombre"].lower():
                    dias_entrega = random.randint(3, 7)
                elif "electrocardiograma" in row["nombre"].lower():
                    dias_entrega = random.randint(1, 2)
                elif "anticuerpo" in row["nombre"].lower():
                    dias_entrega = random.randint(3, 5)
                elif "hepatitis" in row["nombre"].lower():
                    dias_entrega = random.randint(5, 10)
                else:
                    dias_entrega = random.randint(7, 14)  # Por defecto para pruebas no especificadas

                fecha_entrega = fecha_prueba + timedelta(days=dias_entrega)

                # Buscar la categoría y las personas relacionadas
                categoria = Categoria.objects.get(id=row["categoria_id"])
                try:
                    cliente = Persona.objects.get(id=row["cliente_id"])
                except Persona.DoesNotExist:
                    cliente_id = random.randint(1000, 3000)
                    cliente = Persona(id=cliente_id, nombre=f"Cliente-{cliente_id}")  # Creando un cliente temporal

                # Obtener el personal
                try:
                    personal = Persona.objects.get(id=row["personal_id"])
                except Persona.DoesNotExist:
                    personal_id = random.randint(1000, 2000)
                    personal = Persona(id=personal_id, nombre=f"Personal-{personal_id}") 

                # Crear la instancia del Test
                test = Test(
                    nombre=row["nombre"],
                    fecha=fecha_prueba,
                    fecha_entrega=fecha_entrega,
                    estado=row["estado"],
                    observaciones=row["observaciones"] if row["observaciones"] != "N/a" else None,
                    calificacion=int(row["calificacion"]),
                    categoria=categoria,
                    cliente=cliente,
                    personal=personal,
                )
                # Imprimir datos del test para depuración
                
                #para crear los resultados, se toman el nombre y se generan resutlados leatorios por codigo
                
                 # Generar el resultado del test
                resultado_text, interpretacion, detalles = generar_resultado(test.nombre)

                # Crear la instancia de Resultado
                resultado = Resultado(
                    test=test,
                    resultado=resultado_text,
                    fecha=fecha_entrega,
                    observaciones="N/a",
                    interpretacion=interpretacion,
                    detalles=detalles,
                    url_imagen_path=None,
                )
                resultados.append(resultado)
                
                
                print(f"""
                Test creado:
                - Nombre: {test.nombre}
                - Fecha: {test.fecha}
                - Fecha Entrega: {test.fecha_entrega}
                - Estado: {test.estado}
                - Observaciones: {test.observaciones}
                - Calificación: {test.calificacion}
                - Categoría ID: {test.categoria.id}
                - Cliente ID: {test.cliente.id}
                - Personal ID: {test.personal.id}
                """)

                tests.append(test)
                print(f"Resultado creado: {resultado._dict_}")

            # Guardar todos los tests en la base de datos
            Test.objects.bulk_create(tests)
            Resultado.objects.bulk_create(resultados)

            return HttpResponse("Datos de los tests cargados exitosamente.")
        except Exception as e:
            return HttpResponse(f"Error al procesar el archivo: {str(e)}")

    return render(request, "cargar_tests.html")

# Función para generar el resultado del test
def generar_resultado(nombre_test):
    nombre_test = nombre_test.lower()

    if "covid" in nombre_test:
        resultado = random.choice(["Negativo", "Positivo"])
        interpretacion = "Infección activa" if resultado == "Positivo" else "No se detectó el virus"
        detalles = "Prueba PCR realizada correctamente."
    elif "paternidad" in nombre_test:
        resultado = random.choice(["Inclusión", "Exclusión"])
        interpretacion = "Coincidencia de marcadores genéticos" if resultado == "Inclusión" else "No hay relación biológica"
        detalles = "Prueba de ADN realizada con precisión."
    elif "hemograma" in nombre_test:
        resultado = "Normal" if random.random() > 0.2 else "Anormal"
        interpretacion = "Valores dentro de los rangos esperados" if resultado == "Normal" else "Anemia detectada"
        detalles = "Conteo completo de células sanguíneas."
    elif "influenza" in nombre_test:
        resultado = random.choice(["Negativo", "Positivo"])
        interpretacion = "Infección viral activa" if resultado == "Positivo" else "No se detectó el virus"
        detalles = "Prueba rápida de influenza."
    elif "alergia" in nombre_test:
        resultado = random.choice(["Sin alergias", "Alergias detectadas"])
        interpretacion = "Reacción alérgica" if resultado == "Alergias detectadas" else "Sin reacciones"
        detalles = "Panel de alérgenos completado."
    elif "electrocardiograma" in nombre_test:
        resultado = random.choice(["Normal", "Anormal"])
        interpretacion = "Ritmo cardíaco regular" if resultado == "Normal" else "Arritmia detectada"
        detalles = "ECG realizado sin complicaciones."
    elif "anticuerpo" in nombre_test:
        resultado = random.choice(["Positivo", "Negativo"])
        interpretacion = "Presencia de anticuerpos" if resultado == "Positivo" else "No se detectaron anticuerpos"
        detalles = "Prueba serológica completada."
    elif "hepatitis" in nombre_test:
        resultado = random.choice(["Negativo", "Positivo"])
        interpretacion = "Infección detectada" if resultado == "Positivo" else "No se detectó infección"
        detalles = "Análisis para hepatitis realizado."
    else:
        resultado = "Indeterminado"
        interpretacion = "No se pudo interpretar el resultado"
        detalles = "Datos insuficientes para el análisis."

    return resultado, interpretacion, detalles
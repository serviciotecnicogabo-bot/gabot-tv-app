# GABOT STREAMING – App para Android TV / Android

App nativa que abre tu plataforma **GABOT STREAMING** (la que ya tienes en la
nube) a pantalla completa, con ícono propio en el menú del televisor. Por dentro
usa un **WebView** que carga tu URL de Render.

> **Creado por GABOT**

---

## ⭐ CAMINO FÁCIL: que GitHub compile el .apk por ti (sin instalar nada)

Si no quieres usar Android Studio, GitHub puede **compilar el .apk solo** en la
nube y dejarte un **enlace directo** listo para Downloader. Es el camino
recomendado si no sabes programar.

### Paso 1: edita tu URL
Abre `app/src/main/res/values/strings.xml` y pon tu dirección de Render en
`server_url` (ver más abajo).

### Paso 2: sube TODO este proyecto a un repositorio nuevo de GitHub
Crea un repo nuevo (por ejemplo `gabot-tv-app`) y sube todas estas carpetas y
archivos (incluida la carpeta oculta `.github`). Importante: la rama debe
llamarse `main` o `master`.

### Paso 3: espera a que se construya
- Entra a la pestaña **Actions** de tu repo. Verás el proceso "Construir APK
  GABOT" ejecutándose (2-4 minutos).
- Si no arranca solo, entra a **Actions**, elige "Construir APK GABOT" y pulsa
  **Run workflow**.

### Paso 4: toma tu enlace directo para Downloader
- Cuando termine, ve a la sección **Releases** del repo (lado derecho).
- Habrá una entrada **"GABOT STREAMING - ultima APK"** con el archivo
  `gabot-streaming.apk`.
- Copia el enlace de ese archivo. Será algo así:
  `https://github.com/TU-USUARIO/gabot-tv-app/releases/download/apk-latest/gabot-streaming.apk`

### Paso 5: instala en el TV
- En el televisor abre **Downloader** y escribe ese enlace.
- Se descarga el `.apk` y lo instalas. ¡Listo!

> Nota: la primera vez GitHub te puede pedir confirmar que quieres habilitar
> Actions en el repo. Acepta y vuelve a lanzarlo.

---

## 🔧 Paso 1 (igual para ambos caminos): poner tu dirección (OBLIGATORIO)

Abre el archivo:

```
app/src/main/res/values/strings.xml
```

Y cambia esta línea por la URL de tu servidor en Render:

```xml
<string name="server_url">https://TU-APP.onrender.com</string>
```

Ejemplo real:

```xml
<string name="server_url">https://gabot-server.onrender.com</string>
```

Debe ser **https://** (Render ya te da HTTPS). Es lo único que tienes que editar.

---

## 🛠️ OTRO CAMINO: compilar el .apk con Android Studio (en tu PC)

1. Descarga e instala **Android Studio** (gratis) en tu PC.
2. **Open** y selecciona esta carpeta (`gabot-tv-app`).
3. Espera a que Android Studio descargue Gradle y las dependencias (la primera
   vez tarda unos minutos; necesita internet).
4. Menú **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
5. Cuando termine, pulsa **locate** y copia el archivo:
   `app/build/outputs/apk/debug/app-debug.apk`

Ese `app-debug.apk` es tu aplicación lista para instalar.

---

## 📺 Paso 3: instalar en el televisor Android (sideload)

Los Android TV no traen Play Store para apps externas, así que se instala a mano:

**Opción A – con la app "Downloader" (la más fácil):**
1. En el TV instala **Downloader** desde la Play Store.
2. Sube tu `app-debug.apk` a un enlace directo (Google Drive directo, Dropbox,
   o tu propio hosting) y en Downloader escribe esa dirección.
3. Se descarga y te ofrece instalar. Acepta "orígenes desconocidos" si lo pide.

**Opción B – con un USB:**
1. Copia el `.apk` a una memoria USB.
2. Con un explorador de archivos en el TV (ej. "X-plore"), ábrelo e instala.

**Opción C – con ADB (desde la PC):**
```bash
adb connect IP_DEL_TV:5555
adb install app-debug.apk
```
(Activa antes "Depuración USB" / "ADB por red" en los ajustes de desarrollador del TV.)

Al terminar verás el ícono **GABOT STREAMING** en el menú del televisor.

---

## ▶️ Cómo se usa

- Al abrir la app aparece tu **login**. Entra con una **cuenta de cliente**
  (creada desde el panel de admin) que tenga canales asignados.
- Navega con el control remoto; el botón **Atrás** regresa a la pantalla anterior.
- El video se reproduce a pantalla completa.

---

## ❓ Si algo falla

- **Pantalla en blanco:** revisa que la URL en `strings.xml` esté bien escrita y
  que tu servidor de Render esté despierto (en el plan gratis puede tardar unos
  segundos en "despertar" la primera vez).
- **No aparece en el menú del TV:** reinicia el televisor; el ícono usa el
  lanzador leanback que ya viene configurado en el manifest.
- **El video no carga:** confirma que ese canal reproduce bien desde la web en
  una PC; el problema suele ser el link de transmisión, no la app.

**Creado por GABOT**

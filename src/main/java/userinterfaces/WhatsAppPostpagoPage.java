package userinterfaces;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class WhatsAppPostpagoPage {

  public static final Target BTN_MENU_PRINCIPAL =
      Target.the("Boton Menú principal")
          .located(By.xpath("//android.widget.TextView[@text=\"Menú principal\"]"));
  public static final Target LOGO_CLARO_VIDEO =
      Target.the("Logo clarovideo")
          .located(
              By.xpath(
                  "//android.widget.Image[@content-desc=\"clarovideo\" and @text=\"landing_head_logoClarovideo\"]"));

  public static final Target BTN_ACCEDER_CLARO_MUSICA =
      Target.the("Boton Acceder a Claro música")
          .located(By.xpath("//*[@text='Acceder a Claro música']"));

  public static final Target BTN_CLOSE =
      Target.the("Boton Close")
          .located(By.xpath("//android.widget.Button[@content-desc=\"Close\"]"));

  public static final Target BTN_CLOSE_2 =
      Target.the("Boton Close").located(By.xpath("//android.widget.Button[@text=\"Close\"]"));

  public static final Target  LINK_TRATAMIENTO_DATOS =
      Target.the("Link tratamiento de datos")
          .located(
              By.xpath(
                  "//android.view.View[@content-desc=\"https://www.claro.com.co/personas/autogestion/whatsapp/\"]"));

  // Elementos Portal de Pagos
  public static final Target LBL_FACTURA_POSTPAGO_LISTA =
      Target.the("Factura postpago lista")
          .located(By.xpath("//*[contains(@text, '¡Tu factura postpago está lista!')]"));

  public static final Target SELECTOR_MEDIO_PAGO =
      Target.the("Selector medio de pago")
          .located(By.xpath("//android.view.View[@resource-id=\"select\"]"));

  public static final Target BTN_CONTINUAR_PAGO =
      Target.the("Botón Continuar pago").located(By.xpath("//*[@text='Continuar']"));

  // Elementos Formulario Tarjeta
  public static final Target TXT_NUMERO_TARJETA =
      Target.the("Campo número de tarjeta")
          .located(By.xpath("//android.widget.EditText[@resource-id=\"NUMERO_TARJETA\"]"));

  public static final Target TXT_NOMBRE_APELLIDO =
      Target.the("Campo nombre y apellido")
          .located(By.xpath("//android.widget.EditText[@resource-id=\"NOMBRE_TARJETA\"]"));

  public static final Target SELECT_TIPO_DOCUMENTO =
      Target.the("Selector tipo documento")
          .located(By.xpath("//android.view.View[@resource-id=\"TIPO_DOCUMENTO\"]"));

  public static final Target TXT_NUMERO_DOCUMENTO =
      Target.the("Campo número documento")
          .located(By.xpath("//android.widget.EditText[@resource-id=\"NUMERO_DOCUMENTO\"]"));

  public static final Target SELECT_MES_EXPIRACION =
      Target.the("Selector mes expiración")
          .located(By.xpath("//android.view.View[@resource-id=\"FECHA_VENC_MES\"]"));

  public static final Target SELECT_ANIO_EXPIRACION =
      Target.the("Selector año expiración")
          .located(By.xpath("//android.view.View[@resource-id=\"FECHA_VENC_ANNO\"]"));

  public static final Target TXT_CVC =
      Target.the("Campo CVC")
          .located(By.xpath("//android.widget.EditText[@resource-id=\"CODIGO_SEGURIDAD\"]"));

  public static final Target TXT_CORREO_ELECTRONICO =
      Target.the("Campo correo electrónico")
          .located(By.xpath("//android.widget.EditText[@resource-id=\"EMAIL\"]"));

  public static final Target TXT_NUMERO_CELULAR =
      Target.the("Campo número celular")
          .located(By.xpath("//android.widget.EditText[@resource-id=\"TELEFONO\"]"));

  public static final Target SELECT_NUMERO_CUOTAS =
      Target.the("Selector número cuotas")
          .located(By.xpath("//android.view.View[@resource-id=\"CUOTAS\"]"));

  public static final Target BTN_AUMENTAR_CUOTAS = 
      Target.the("Botón aumentar cuotas").located(By.xpath("//android.widget.Button[@text=\"+\"]"));

  public static final Target BTN_DISMINUIR_CUOTAS = 
      Target.the("Botón disminuir cuotas").located(By.xpath("//android.widget.Button[@text=\"-\"]"));

  public static final Target CHK_GUARDAR_TARJETA_HOGAR = 
      Target.the("Check Guardar tarjeta Hogar").located(By.xpath("//android.widget.CheckBox[@resource-id=\"ASOCIAR_CLPAY\"]"));

  public static final Target TXT_FECHA_EXPIRACION =
      Target.the("Campo Fecha Expiración Hogar").located(By.xpath("//android.widget.EditText[@resource-id=\"FECHA_VENC\"]"));

  public static final Target TOGGLE_GUARDAR_TARJETA =
      Target.the("Toggle guardar tarjeta").located(By.xpath("//android.view.View[@text=\"Si\"]"));

  public static final Target TOGGLE_GUARDAR_TARJETA_NO =
      Target.the("Toggle guardar tarjeta opcion No")
          .located(By.xpath("//android.view.View[@text=\"¿Guardar esta tarjeta? No\"]"));

  public static final Target BTN_CONFIRMAR_FINAL =
      Target.the("Botón Confirmar final").located(By.xpath("//android.widget.Button[@resource-id=\"mySubmit_\"]"));

  public static final Target BTN_PAGAR_FINAL =
      Target.the("Botón Pagar final").located(By.xpath("//*[@text='Pagar']"));

  public static final Target BTN_CANCELAR_FINAL =
      Target.the("Botón Cancelar final").located(By.xpath("//*[@text='Cancelar']"));

  public static final Target BTN_TARJETA_CREDITO =
      Target.the("Tarjeta crédito")
          .located(By.xpath("//android.widget.TextView[@text=\"Tarjeta crédito\"]"));

  public static final Target BTN_DEBITO_BANCARIO_PSE =
      Target.the("Débito Bancario PSE")
          .located(By.xpath("//android.widget.TextView[@text=\"Débito Bancario PSE\"]"));

  public static final Target SELECT_BANCO =
      Target.the("Selector de Banco")
          .located(By.xpath("//android.view.View[@resource-id='BANCO'] | //android.widget.Spinner[contains(@resource-id, 'BANCO')]"));

  public static final Target TXT_NOMBRE_TITULAR_PSE =
      Target.the("Campo nombre del titular PSE")
          .located(By.xpath("//android.widget.EditText[@resource-id=\"TITULAR\"]"));

  public static final Target SELECT_TIPO_CLIENTE =
      Target.the("Selector tipo de cliente")
          .located(By.xpath("//android.view.View[@resource-id=\"TIPO_CLIENTE\"]"));
  public static final Target SELECT_PERSONA_NATURAL =
          Target.the("Persona Natural")
                  .located(By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Persona Natural\"]"));
  public static final Target TXT_DIRECCION =
      Target.the("Campo dirección")
          .located(By.xpath("//android.widget.EditText[@resource-id=\"DIRECCION\"]"));

  public static final Target BTN_BOTON_BANCOLOMBIA =
      Target.the("Opción Botón Bancolombia")
          .located(By.xpath("//android.widget.TextView[@text=\"Botón Bancolombia\"]"));

  public static final Target BTN_DAVIPLATA =
      Target.the("Opción Daviplata")
          .located(By.xpath("//android.widget.TextView[contains(@text, 'Daviplata')] | //*[@text='Daviplata']"));

  public static final Target BTN_CONFIRMAR_DAVIPLATA =
      Target.the("Botón Confirmar Daviplata")
          .located(By.xpath("//android.widget.Button[@resource-id=\"mySubmit_\"]"));

  public static final Target TXT_USUARIO_BANCOLOMBIA =
      Target.the("Campo Usuario Bancolombia")
          .located(By.xpath("//android.view.View[@resource-id=\"root\"]/android.view.View/android.view.View/android.view.View/android.widget.EditText"));

  public static final Target BTN_CONTINUAR_BANCOLOMBIA =
      Target.the("Botón Continuar Bancolombia")
          .located(By.xpath("//android.widget.Button[@text='Continuar'] | //android.view.View[@text='Continuar'] | //*[@text='Continuar' and contains(@class, 'Button')] | //android.widget.Button[contains(@text, 'Continuar')]"));

  public static final Target TXT_RECHAZAR =
      Target.the("Texto Rechazar").located(MobileBy.xpath("//*[@text='Rechazar']"));

  public static final Target TXT_MARCAR_COMO_LEIDO =
      Target.the("Texto MARCAR COMO LEÍDO")
          .located(MobileBy.xpath("//android.widget.Button[@content-desc=\"Marcar como leído\"]"));

  public static final Target LBL_MAS =
      Target.the("Texto Más").located(By.xpath("//*[@text='Más']"));
  public static final Target BTN_LECTOR_PDF_DRIVE =
      Target.the("Boton Lector de PDF de Drive")
          .located(By.xpath("//*[@text='Lector de PDF de Drive']"));

  public static final Target TXT_CONTRASENA_FACTURA =
      Target.the("Campo contraseña factura")
          .located(By.id("com.google.android.apps.docs:id/password"));

  public static final Target URL_FACTURA_DESCARGADA =
      Target.the("URL factura descargada").located(By.id("com.android.chrome:id/url_bar"));

  public static final Target IMAGEN_CLARO_VIDEO =
      Target.the("Foto como registrarse Claro Video").located(By.id("com.whatsapp:id/image"));

  public static final Target IMAGEN_CLARO_MUSICA =
      Target.the("Foto como registrarse Claro Musica").located(By.id("com.whatsapp:id/image"));

  public static final Target BTN_CLARO_VIDEO =
      Target.the("Boton Claro Video")
          .located(
              By.xpath(
                  "//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\" and @text=\"Claro video\"]"));

  public static final Target BTN_CLARO_MUSICA =
      Target.the("Boton Claro Musica")
          .located(
              By.xpath(
                  "//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\" and @text=\"Claro música\"]"));
  public static final Target LBL_EQUIPO_REGISTRADO =
      Target.the("Texto La consulta realizada indica que tu dispositivo se encuentra registrado")
          .located(
              By.xpath(
                  "//android.widget.TextView[@resource-id=\"com.whatsapp:id/bottom_message\" and @text=\"\uD83D\uDE0A La consulta realizada indica que tu dispositivo se encuentra registrado\"]"));
  public static final Target LBL_IMEI_REGISTRADO =
      Target.the("Texto Tu IMEI ya se encuentra registrado.")
          .located(By.xpath("//*[contains(@text, 'Tu IMEI ya se encuentra registrado.')]"));
  public static final Target BTN_SELECCIONA_TU_LEALTAD =
      Target.the("Boton Selecciona de la sesión tu lealtad merece mas")
          .located(
              By.xpath(
                  "(//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\"])[3]"));

  public static final Target BTN_PERMISO_UBICACION =
      Target.the("Botón mientras la app está en uso")
          .located(By.id("com.android.permissioncontroller:id/permission_message"));
  public static final Target BTN_ACEPTAR_PERMISO =
      Target.the("Boton Aceptar").located(By.xpath("//*[@text='Aceptar']"));
  public static final Target SMS_PERMISO_LLAMADAS =
      Target.the("Mensaje ¿Permitir que Mi Claro haga y administre las llamadas telefónicas?")
          .located(
              By.xpath(
                  "//*[@text='¿Permitir que Mi Claro haga y administre las llamadas telefónicas?']"));
  public static final Target SMS_PERMISO_NOTIFICACIONES =
      Target.the("Mensaje ¿Permitir que Mi Claro te envíe notificaciones? ")
          .located(By.xpath("//*[@text='¿Permitir que Mi Claro te envíe notificaciones?']"));
  public static final Target BTN_OMITIR =
      Target.the("Boton de Omitir").located(By.xpath("//*[@text='Omitir']"));
  public static final Target LBL_BIENVENIDA =
      Target.the("Texto ¡Te damos la bienvenida!")
          .located(By.xpath("//*[@text='¡Te damos la bienvenida!']"));
  public static final Target CHECK_TC =
      Target.the("Check Acepto los términos y condiciones, el tratamiento de mis datos")
          .located(By.id("com.clarocolombia.miclaro:id/iv_protocol"));
  public static final Target TXT_AUTORIZACION_VELOCIDAD =
      Target.the("Texto de Autorización de medición de velocidad")
          .located(By.xpath("//*[@text='Autorización de medición de velocidad']"));

  public static final Target LBL_PORTAL_PAGOS_RECARGAS =
      Target.the("Texto Portal de PAGOS Y RECARGAS")
          .located(By.xpath("//*[@text='Portal de PAGOS Y RECARGAS']"));

  public static final Target LBL_SESION_CERRADA_POR_SEGURIDAD =
      Target.the("Texto Tu sesión se ha cerrado por seguridad")
          .located(
              By.xpath(
                  "//*[@text='Por seguridad, hemos cerrado tu sesión en Mi Claro. Ingresa de nuevo para administrar tus servicios']"));

  public static final Target LBL_PRIVACIDAD =
      Target.the("Texto Tu privacidad es importante para nosotros")
          .located(By.xpath("//*[@text='Tu privacidad es importante para nosotros']"));

  public static final Target CHECK_SELECCIONAR_MEDIO_PAGO =
          Target.the("Check para seleccionar medio de pago")
                  .located(By.xpath("//android.view.View[@resource-id=\"select\"]"));

  public static final Target BTN_MIS_TARJETAS_REGISTRADAS =
      Target.the("Opción Mis tarjetas registradas")
          .located(By.xpath("//android.widget.TextView[@text=\"Mis tarjetas registradas\"]"));

  public static final Target BTN_INICIAR_SESION_PORTAL =
      Target.the("Botón Iniciar Sesión Portal")
          .located(By.xpath("//android.widget.Button[@text='Iniciar Sesión'] | //android.view.View[@text='Iniciar Sesión'] | //*[@text='Iniciar Sesión']"));

}

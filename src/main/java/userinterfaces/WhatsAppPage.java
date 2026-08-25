package userinterfaces;

import io.appium.java_client.MobileBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;

public class WhatsAppPage extends PageObject {

  public static final Target LBL_WHATSAPP =
          Target.the("Texto de WhatsApp").located(By.id("toolbar_logo"));

  public static final Target BTN_ENVIAR_MENSAJE =
          Target.the("Boton enviar mensaje").located(By.id("fab"));

  public static final Target BTN_LUPA =
          Target.the("Boton lupa").located(By.id("com.whatsapp:id/search_icon"));

  public static final Target BTN_LUPA_PRINCIPAL =
          Target.the("Boton lupa").located(By.id("search_icon"));

  public static final Target TXT_BUSCAR_TEXTO =
          Target.the("Caja de texto buscar nombre o numero").located(By.xpath("//android.widget.EditText[@text=\"Buscar nombre o número\"]"));

  public static final Target TXT_BUSCAR_TEXTO_PRINCIPAL =
          Target.the("Caja de texto buscar").located(By.id("search_input"));

  public static final Target BTN_MENU_ITEM =
          Target.the("Boton Más opciones WhatsApp").located(By.id("menuitem_overflow"));

  public static final Target TXT_ENVIAR_MENSAJE =
          Target.the("Caja de texto enviar mensaje")
                  .located(By.id("entry"));

  public static final Target BTN_CERRAR =
          Target.the("Bonton X").located(By.xpath("//*[@class='android.widget.ImageButton']"));

  public static final Target BTN_ENVIAR = Target.the("Boton enviar mensaje").located(By.id("send"));

  public static final Target BTN_SI = Target.the("Boton Si")
          .located(By.id("//android.widget.FrameLayout[@content-desc=\"Si\"]"));

  public static final Target BTN_SI2 = Target.the("Boton Si")
          .located(By.xpath("(//android.widget.FrameLayout[@content-desc=\"Si\"])[1]"));

  public static final Target BTN_SI3 = Target.the("Boton si 3")
          .located(By.xpath("//android.widget.FrameLayout[@content-desc=\"Si\"]"));

  public static final Target BTN_NO = Target.the("Boton No")
          .located(By.xpath("//android.widget.FrameLayout[@content-desc=\"No\"]"));

  public static final Target BTN_NO2 = Target.the("Boton No 2")
          .located(By.xpath("(//android.widget.FrameLayout[@content-desc=\"No\"])[2]"));

  public static final Target LBL_TRATAMIENTO_DATOS =
          Target.the(
                          "¿Autorizas el tratamiento de tus datos personales y aceptas los T&C de nuestro canal de atención digital?")
                  .located(
                          By.xpath(
                                  "//*[@text='¿Autorizas el tratamiento de tus datos personales y aceptas los T&C de nuestro canal de atención digital?']"));

  public static final Target BTN_VER_MENU_PREPAGO =
          Target.the("Boton Ver menú prepago ").located(By.xpath("//*[@text='Ver menú prepago']"));

  public static final Target LBL_SIN_SALDO =
          Target.the("Texto Actualmente no tienes saldo disponible")
                  .located(
                          By.xpath(
                                  "//*[@text='Actualmente no tienes saldo disponible.\n"
                                          + "\n"
                                          + "Elige una de estas opciones y sigue conectado  \uD83D\uDC47']"));

  public static final Target CHAT_CLARO =
          Target.the("Chat Claro").located(By.xpath("//*[@text='Claro Colombia']"));

  public static final Target LBL_VISTA_PREVIA =
          Target.the("Texto vista previ del destino ").located(By.xpath("//*[@text='claro.com.co']"));

  public static final Target LBL_MEJORA_TU_PLAN =
          Target.the("Texto Mejora tu plan").located(By.xpath("//*[@text='Mejora tu plan']"));

  public static final Target LBL_PAQUETE_ACTIVO =
          Target.the("Texto paquete activo")
                  .located(By.xpath("//*[contains(@text, 'Actualmente tienes activo')]"));

  public static final Target LBL_NOMBRE_PLAN =
          Target.the("Texto Nombre del plan")
                  .located(By.xpath("//*[contains(@text, 'Nombre del plan')]"));
  public static final Target LBL_MENU_LINEAS =
          Target.the("Texto del menú de líneas")
                  .located(By.xpath("//*[contains(@text, 'Líneas postpago')]"));
  public static final Target TXT_CAJA_MENSAJE = Target.the("Caja texto").located(By.id("entry"));

  public static final Target LBL_CONSUMO_PAQUETES =
          Target.the("Texto Tu consumo de paquetes a la fecha es")
                  .located(By.xpath("//*[contains(@text, 'Tu consumo de paquetes a la fecha es')]"));

  public static final Target BTN_INSTALAR =
          Target.the("Boton Instalar").located(By.xpath("//*[@text='Instalar']"));

  public static final Target BTN_CANCELAR =
          Target.the("Boton Cancelar").located(By.xpath("//*[@text='Cancelar']"));

  public static final Target TXT_BIENVENIDO_CLARO =
          Target.the("Texto ¡Bienvenido al nuevo Claro música!")
                  .located(By.xpath("//*[@text='¡Bienvenido al nuevo Claro música!']"));

  public static final Target LOG_CLARO_MUSICA =
          Target.the("Logo Claro Musica").located(By.xpath("//*[@contentDescription='Cm Logo']"));

  public static final Target TXT_PERMISO_NOTIFICACION =
          Target.the("Texto ¿Permitir que Claro música te envíe notificaciones?")
                  .located(By.xpath("//*[@text='¿Permitir que Claro música te envíe notificaciones?']"));

  public static final Target BTN_NO_PERMITIR =
          Target.the("Boton No permitir").located(By.xpath("//*[@text='No permitir']"));

  public static final Target BTN_SELECCIONA_MAS =
          Target.the("Boton Selecciona del menu tu lealtad merece más ")
                  .located(
                          By.xpath(
                                  "(//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\"])[3]"));

  public static final Target URL_CLARO_MUSICA2 =
          Target.the("URL Claro Musica")
                  .located(By.xpath("//*[@contentDescription='https://bit.ly/3Dw3dk9']"));

  public static final Target IMAG_CLARO_DRIVE =
          Target.the("Imagen Claro Drive")
                  .located(
                          By.xpath(
                                  "//android.webkit.WebView[@text=\"Claro drive\"]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.widget.Image"));

  public static final Target LBL_SIGNO_PRECIO =
          Target.the("Texto precio").located(By.id("select_list_item_title"));

  public static final Target LBL_MAS_OPCIONES =
          Target.the("Texto Más opciones").located(By.xpath("//*[@text='Más opciones']"));

  public static final Target BTN_SELECCIONA2 =
          Target.the("Boton Selecciona")
                  .located(
                          By.xpath(
                                  "(//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\"])[3]"));

  public static final Target BTN_CONTINUAR_RECARGA =
          Target.the("Boton Continuar recarga").located(By.xpath("//*[@text='Continuar recarga']"));

  public static final Target LBL_TARJETA_DEBITO_CREDITO =
          Target.the("Texto Tarjeta Débito o Crédito")
                  .located(By.xpath("//*[@text='Tarjeta Débito o Crédito']"));
  public static final Target LBL_SIN_CUENTA_NEQUI =
          Target.the("Texto Sin cuenta nequi")
                  .located(
                          By.xpath(
                                  "//*[@text='\uD83D\uDE25 Parece que este número no tiene una cuenta en Nequi.\n"
                                          + "\n"
                                          + "Puedes intentarlo de nuevo con otra cuenta Nequi o elegir otro medio de pago \uD83D\uDC47']"));
  public static final Target LBL_CHATS =
          Target.the("Texto chat con nombre dinámico").locatedBy("//*[@text='{0}']");

  public static final Target BTN_ELIMINAR_CHAT =
          Target.the("Boton Eliminar chat").located(By.id("menuitem_conversations_delete"));
  public static final Target BTN_MAS_OPCIONES =
          Target.the("Boton Más opciones").located(By.id("menuitem_overflow"));

  public static final Target LBL_MENSAJES =
          Target.the("Mensajes del chat")
                  .located(
                          MobileBy.AndroidUIAutomator(
                                  "new UiSelector().className(\"android.widget.TextView\")"));

  public static final Target LBL_MENU_ANTERIOR =
          Target.the("Menu anterior LDI")
                  .located(
                          MobileBy.xpath("(//android.widget.FrameLayout[@content-desc=\"Menú anterior\"])[2]"));

  public static final Target LBL_MENU_ANTERIOR2 =
          Target.the("Menu anterior LDI")
                  .located(
                          MobileBy.xpath("(//android.widget.FrameLayout[@content-desc=\"Menú anterior\"])[3]"));

  public static final Target BTN_ENVIAR_2 =
          Target.the("Botón Enviar")
                  .located(
                          MobileBy.xpath(
                                  "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='enviar']"));

  public static final Target BTN_SELECCIONA_PQ_TODO_INCLUIDO =
          Target.the("Boton Selecciona")
                  .located(
                          MobileBy.xpath(
                                  "(//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\"])[2]"));

  public static final Target BTN_SELECCIONA_ESTADO_PQR =
          Target.the("Boton Selecciona verifica el estado de tus PQR")
                  .located(
                          MobileBy.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\" and @text=\"Selecciona\"]"));

  public static final Target BTN_VER_PAQUETES =
          Target.the("Boton Ver paquetes").located(By.xpath("//*[@text='Ver paquetes']"));

  public static final Target BTN_CONFIRMAR =
          Target.the("Boton Confirmar").located(By.xpath("//*[@text='Confirmar']"));

  public static final Target BTN_MEDIOS_DE_PAGO =
          Target.the("Boton Medios de pago")
                  .located(
                          By.xpath(
                                  "//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\" and @text=\"Medios de pago\"]"));
  public static final Target BTN_SELECCIONA_RECARGAS =
          Target.the("Boton Selecciona recargas")
                  .located(
                          By.xpath(
                                  "(//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\"])[3]"));

  public static final Target LBL_MENSAJE_ACTUALIZACION =
          Target.the("Mensaje de actualización del sistema")
                  .located(
                          By.xpath(
                                  "//*[contains(@text, 'Nuestro sistema registra un proceso de actualización')]"));
  public static final Target BTN_NO_ACTUALIZACION =
          Target.the("Botón No para rechazar actualización").located(By.xpath("//*[@text='No']"));

  public static final Target BTN_SELECCIONA_SOPORTE =
          Target.the("Botón Selecciona Soporte")
                  .located(
                          By.xpath(
                                  "(//android.widget.FrameLayout[@resource-id=\"com.whatsapp:id/button\"])[2]"));

  public static final Target BTN_REPRODUCIR_VIDEO =
          Target.the("Botón reproducir video").located(By.id("com.whatsapp:id/cancel_download"));
  public static final Target BTN_SELECCIONA_PQ_INTERNACIONALES =
          Target.the("Boton Selecciona sección paquetes internacionales")
                  .located(
                          MobileBy.xpath(
                                  "//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\"]"));

  public static final Target BTN_SELECCIONA_TU_LEALTAD_2 =
          Target.the("Boton Selecciona del menu tu lealtad merece más ")
                  .located(
                          By.xpath(
                                  "(//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\"])[2]"));
  public static final Target BTN_SELECCIONA_PQ_INTERNACIONALES_2 =
          Target.the("Boton Selecciona sección paquetes internacionales")
                  .located(
                          MobileBy.xpath(
                                  "(//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\"])[2]"));

  public static final Target BTN_SELECCIONA_PQ_TODO_INCLUIDO_NUEVO =
          Target.the("Botón Selecciona")
                  .located(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Selecciona\")"));

  // Todos los "Selecciona"
  public static final Target BTN_SELECCIONA_ALL =
          Target.the("Botones Selecciona").located(MobileBy.id("com.whatsapp:id/button_content"));

  public static final Target TXT_COPIA_SEGURIDAD =
          Target.the("Botones Selecciona")
                  .located(MobileBy.xpath("//*[@text='Copia de seguridad cifrada de extremo a extremo']"));

  public static final Target BTN_VACIAR_CHAT =
          Target.the("Botones Selecciona").located(MobileBy.id("com.whatsapp:id/primary_button"));

  public static final Target LBL_CONOCE_OPCIONES =
          Target.the("Mensaje Conoce las opciones")
                  .locatedBy("//*[contains(@text,'Conoce las opciones')]");

  public static final Target TXT_BIENVENIDA_WHATSAPP =
          Target.the("Texto Te damos la bienvenida a WhatsApp")
                  .locatedBy("//*[contains(@text,'Te damos la bienvenida a WhatsApp')]");

  public static final Target BTN_COMPRAR_PAQ_PREPAGO =
          Target.the("Boton Comprar paq. prepago")
                  .located(MobileBy.xpath("//*[@text='Comprar paq. prepago']"));

  public class AppConstants {
    public static final String WHATSAPP_PACKAGE = "com.whatsapp";
  }

  public static final Target LOGO_WHATSAPP =
          Target.the("Logo WhatsApp")
                  .located(By.xpath("//android.widget.ImageView[@resource-id=\"com.whatsapp:id/toolbar_logo\"]"));

  public static final Target IMG_CONFIGUR_PUNTO_ACCESO =
          Target.the("Imagenconfigura tu punto de acceso")
                  .located(By.xpath("(//android.widget.Button[@content-desc=\"Agrandar foto\"])[1]"));

  public static final Target IMG_AGRANDAR_FOTO =
          Target.the("Cualquier imagen con boton agrandar foto")
                  .located(By.xpath("//android.widget.Button[@content-desc=\"Agrandar foto\"]"));

  public static final Target BOTON_ATRAS =
          Target.the("Boton atras")
                  .located(By.xpath("//android.widget.ImageButton[@content-desc=\"Atrás\"]"));

  public static final Target IMG_CONFIGURA_MODO_RED_2 =
          Target.the("Imagen Configura tu modo de red (Indice 2)")
                  .located(By.xpath("(//android.widget.Button[@content-desc=\"Agrandar foto\"])[2]"));

  public static final Target BTN_SI_AUTORIZO =
          Target.the("Boton Si autorizo")
                  .located(By.xpath("//android.widget.FrameLayout[@content-desc=\"Si autorizo\"]"));

  public static final Target PROTEGUE_INFO_SENSIBLE =
          Target.the("Protege tu informacion sensible")
                  .located(By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/bottom_message\"]"));

  public static final Target BTN_TU_FACTURA =
          Target.the("Boton tu factura")
                  .located(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.whatsapp:id/select_list_items\"]/android.widget.RadioButton[1]/android.widget.RadioButton[@resource-id=\"com.whatsapp:id/select_list_item_radio_button\"]"));

  public static final Target BTN_SOPORTE_SERVICIOS =
          Target.the("Boton Soporte y Servicios")
                  .located(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.whatsapp:id/select_list_items\"]/android.widget.RadioButton[2]/android.widget.RadioButton[@resource-id=\"com.whatsapp:id/select_list_item_radio_button\"]"));

  public static final Target BTN_TODO_SOBRE_TU_PLAN =
          Target.the("Boton Todo sobre tu plan")
                  .located(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.whatsapp:id/select_list_items\"]/android.widget.RadioButton[3]/android.widget.RadioButton[@resource-id=\"com.whatsapp:id/select_list_item_radio_button\"]"));

  public static final Target BTN_ROAMING_INTERNACIONAL =
          Target.the("Boton roaming internacional")
                  .located(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.whatsapp:id/select_list_items\"]/android.widget.RadioButton[4]/android.widget.RadioButton[@resource-id=\"com.whatsapp:id/select_list_item_radio_button\"]"));

  public static final Target BTN_ENVIAR_MENU =
          Target.the("Boton enviar que se encuentra en el menu")
                  .located(By.xpath("//android.widget.Button[@resource-id=\"com.whatsapp:id/select_list_button\"]"));

  public static final Target BTN_LINEA_MI_NEGOCIO =
          Target.the("Boton linea de mi negocio")
                  .located(By.xpath("//android.widget.TextView[@resource-id=\"com.whatsapp:id/button_content\" and @text=\"Línea de mi negocio\"]"));

  public static final Target BTN_DATOS =
          Target.the("Boton Datos")
                  .located(By.xpath("//android.widget.FrameLayout[@content-desc=\"Datos\"]"));

  public static final Target BTN_VER_PQR =
          Target.the("Boton ver PQR")
                  .located(By.xpath("//android.widget.FrameLayout[@content-desc=\"Ver PQRs\"]"));

  public static final Target FINALIZAR_CHAT =
          Target.the("Boton finalizar chat")
                  .located(By.xpath("//android.widget.FrameLayout[@content-desc=\"Finalizar chat\"]"));

  public static final Target MENSAJE_AL_DIA_PAGOS =
          Target.the("Tu línea se encuentra al día en pagos")
                  .located(By.xpath(
                          "//android.widget.TextView[@resource-id='com.whatsapp:id/message_text' " +
                                  "and contains(@text,'En este momento, tu línea') " +
                                  "and contains(@text,'se encuentra al día en pagos')]"
                  ));

  public static final Target BTN_ESTADO_SERVICIOS =
          Target.the( "Boton estado y servicios")
                  .located(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.whatsapp:id/select_list_items\"]/android.widget.RadioButton[1]/android.widget.RadioButton[@resource-id=\"com.whatsapp:id/select_list_item_radio_button\"]"));

  public static final Target BTN_TUS_PQR_RADICADOS =
          Target.the( "Boton tus PQR radicados")
                  .located(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.whatsapp:id/select_list_items\"]/android.widget.RadioButton[2]/android.widget.RadioButton[@resource-id=\"com.whatsapp:id/select_list_item_radio_button\"]"));

  public static final Target BTN_CONOCE_PLAN =
          Target.the( "Boton conoce tu plan")
                  .located(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.whatsapp:id/select_list_items\"]/android.widget.RadioButton[1]/android.widget.LinearLayout"));

  public static final Target BTN_CONSULTA_TU_CONSUMO =
          Target.the( "Boton consulta tu consumo")
                  .located(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.whatsapp:id/select_list_items\"]/android.widget.RadioButton[2]/android.widget.LinearLayout"));

  public static final Target BTN_ESTADO_SERVICIOS_DESCRIPCION =
          Target.the("Estado de servicios descripcion")
                  .located(By.xpath("//android.widget.TextView[@resource-id='com.whatsapp:id/select_list_item_description' " + "and contains(@text,'Identifica fallas')]"));

  public static final Target BTN_TUS_PQR_RADICADOS_DESCRIPCION=
          Target.the("Tus PQR Radicados descripcion")
                  .located(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.whatsapp:id/select_list_items\"]/android.widget.RadioButton[2]/android.widget.LinearLayout"));

  public static final Target BTN_SELECCIONA =
          Target.the("Boton seleccionar")
                  .located(By.xpath("(//android.widget.FrameLayout[@resource-id=\"com.whatsapp:id/button\"])[2]"));

}


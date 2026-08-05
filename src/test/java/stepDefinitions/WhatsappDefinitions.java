package stepDefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.wait.EsperarYClickSeleccionaEnUltimoMensaje;
import interactions.wait.WaitFor;
import interactions.wait.WaitForResponse;
import models.User;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import tasks.*;
import tasks.CompraTusPaquetes.*;
import tasks.CompraWhatsApp.MenuCompraWhatsApp;
import tasks.ConsultarOtraCuenta.ConsultarOtraCuenta;
import tasks.HazTusRecargas.DireccionamientoMediosPago;
import tasks.HazTusRecargas.HazTusRecargas;
import tasks.HazTusRecargas.SeleccionarValorRecargas;
import tasks.HazTusRecargas.ValidarMenuRecargas;
import tasks.OtrasOpciones.*;
import tasks.TodoSobreTuLinea.*;
import tasks.TusEquipos.*;
import utils.CapturaDePantallaMovil;
import utils.EvidenciaUtils;
import utils.TestDataProvider;
import utils.WordAppium;

public class WhatsappDefinitions {

  private final User user = TestDataProvider.getRealUser();

  @Before
  public void initScenario(Scenario scenario) {
    OnStage.setTheStage(new OnlineCast());
    WordAppium.inicializarPlantillaReporte();
    EvidenciaUtils.reiniciarContador(); // Reinicia el conteo de pasos para este escenario
  }

  @Given("^que el usuario ingresa a WhatsAPP$")
  public void ingresarWhatsapp() {
    theActorCalled(" ").attemptsTo(IngresarWhatsApp.ingresar());
  }

  @And("^valida version de la App$")
  public void validarVersionApp() {
    theActorInTheSpotlight().attemptsTo(ValidarVersionApp.validarVersionApp());
  }

  @And("^buscar el chat de Claro Colombia$")
  public void buscarChatClaro() {
    theActorInTheSpotlight().attemptsTo(BuscarChatClaro.buscarChatClaro());
  }

  @And("^se inicia el chat con Claro Colombia$")
  public void iniciarChatClaro() {
    theActorInTheSpotlight().attemptsTo(IniciarChatClaro.iniciarChatClaro());
    ReportHooks.setLinea(user.getNumeroPre());
  }

  @And("^seleccionar linea de consulta$")
  public void seleccionarLineaConsulta() {
    theActorInTheSpotlight().attemptsTo(SeleccionarLineaConsulta.seleccionarLineaConsulta());
    ReportHooks.setLinea(user.getNumeroPre());
  }

  @And("^validar politica de tratamientos de datos$")
  public void validarPoliticaTratamiendoDatos() {
    theActorInTheSpotlight().attemptsTo(ValidarTratamientoDatos.validarTratamientoDatos());
  }

  @And("^seleccionar soporte tecnico en opciones rapidas$")
  public void seleccionarSoporteTecnico() {
    theActorInTheSpotlight().attemptsTo(OpcionesRapidas.opcionesRapidas());
  }

  @And("^escoger linea de mi negocio datos$")
  public void escogerLineaMiNegocioDatos() {
    theActorInTheSpotlight().attemptsTo(LineaMiNegocioDatos.lineaMiNegocioDatos());
  }

  @And("^escoger linea de mi negocio llamadas$")
  public void escogerLineaMiNegocioLamadas() {
    theActorInTheSpotlight().attemptsTo(LineaMiNegocioLlamadas.lineaMiNegocioLlamadas());
  }

  @And("^escoger linea de mi negocio Otros opcion 1$")
  public void escogerLineaMiNegocioOtros1() {
    theActorInTheSpotlight().attemptsTo(LineaMiNegocioOtros1.lineaMiNegocioOtros1());
  }

  @And("^escoger linea de mi negocio Otros opcion 2$")
  public void escogerLineaMiNegocioOtros2() {
    theActorInTheSpotlight().attemptsTo(LineaMiNegocioOtros2.lineaMiNegocioOtros2());
  }

  @And("^escoger detalles de tu plan$")
  public void escogerDetallesPlan() {
    theActorInTheSpotlight().attemptsTo(DetallesPlan.detallesPlan());
  }

  @And("^escoger consulta tu consumo$")
  public void escogerConsultaConsumo() {
    theActorInTheSpotlight().attemptsTo(ConsultaCosumo.consultaConsumo());
  }

  @And("^escoger validar tu factura y validar consulta de consumo$")
  public void escogerValidarFacturaConsultaConsumo() {
    theActorInTheSpotlight().attemptsTo(ValidarFacturaConsultaConsumo.validarFacturaConsultaConsumo());
  }

  @And("^escoger estado de servicios Datos$")
  public void escogerEstadoServiciosDatos() {
    theActorInTheSpotlight().attemptsTo(EstadoServiciosDatos.estadoServiciosDatos());
  }

  @And("^escoger soporte y servicio/ PQR$")
  public void escogerSoporteServiciosPQR() {
    theActorInTheSpotlight().attemptsTo(SoporteServiciosPQR.soporteServiciosPQR());
  }

  @And("^escoger todo sobre tu plan/ Conoce tu plan$")
  public void escogerTodoSobrePlanConocePlan() {
    theActorInTheSpotlight().attemptsTo(TodoSobrePlanConocePlan.todoSobrePlanConocePlan());
  }

  @And("^escoger todo sobre tu plan/ Conoce tu consumo$")
  public void escogerTodoSobrePlanConoceConsumo() {
    theActorInTheSpotlight().attemptsTo(TodoSobrePlanConoceConsumo.todoSobrePlanConoceConsumo());
  }

  @And("^escoger Todo sobre tu plan/ Modifica tu plan$")
  public void escogerTodoSobrePlanModificaPlan() {
    theActorInTheSpotlight().attemptsTo(TodoSobrePlanModificaPlan.todoSobrePlanModificaPlan());
  }

  @And("^escoger Todo sobre tu plan/ Cancelar plan$")
  public void escogerTodoSobrePlanCancelarPlan() {
    theActorInTheSpotlight().attemptsTo(TodoSobrePlanCancelarPlan.todoSobrePlanCancelarPlan());
  }

  @And("^escoger Roaming internacional$")
  public void escogerRoamingInternacional() {
    theActorInTheSpotlight().attemptsTo(RoamingInternacional.roamingInternacional());
  }

  @And("^escoger Compra por WhatsApp$")
  public void escogerCompraWhatsApp() {
    theActorInTheSpotlight().attemptsTo(CompraWhatsApp.compraWhatsApp());
  }

  @And("^Vaciar chat$")
  public void vaciarChat() {
    theActorInTheSpotlight()
            .attemptsTo(CerrarCasoYVaciarChat.cerrarCasoYVaciarChat());
  }
}

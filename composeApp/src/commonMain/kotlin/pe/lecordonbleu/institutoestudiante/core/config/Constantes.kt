package pe.lecordonbleu.institutoestudiante.core.config

object Constantes {

    var SUCCESS = 200
    var AUTH = "Bearer" + " "
    var AUTH_PHOTO = "https://graph.microsoft.com/v1.0/me/photo/"

   //var BASE_ROOT_INTRANET = "http://192.168.200.60:8080" // localhost
    var BASE_ROOT_INTRANET = "http://74.249.92.43:8080" // desarrollo
   // var BASE_ROOT_INTRANET = "http://sslcbpsaa.eastus2.cloudapp.azure.com:8080"
    //var BASE_ROOT_INTRANET = "http://sslcbpopen.eastus2.cloudapp.azure.com:8080"

    var URL_BASE_INTRANET = "/saa-rest/webresources/intranetSAA/"
    var URL_BASE_GENERAL = "/saa-rest/webresources/"

    var BASE_DOMAIN = "https://devalumno"    //cuenta corriente
    //var BASE_DOMAIN = "https://prealumno"    //cuenta corriente
    //var BASE_DOMAIN = "https://alumno"    //cuenta corriente

    var BASE_PDF_FLYWIRE = "https://predataread.cordonbleu.edu.pe:8081/verboletas/boleta/"
    //var BASE_PDF_FLYWIRE = "https://dataread.cordonbleu.edu.pe:8082/verboletas/boleta/"


    var URL_PDF_FLYWIREPECANO = "https://devintegracion.cordonbleu.edu.pe/resources/boleta/"
    //var URL_PDF_FLYWIREPECANO = "https://preintegracion.cordonbleu.edu.pe/resources/boleta/"
    //var URL_PDF_FLYWIREPECANO = "https://integracion.cordonbleu.edu.pe/resources/boleta/"

    var BASE_FICHA_MTR = "https://alumno.ilcb.edu.pe/"

    //var RECIPIENT_DOMAIN = "LEP" //Universidad
    var RECIPIENT_DOMAIN = "ILP" //Instituto

    var ID_SISTEMA = 24  //Universidad 23 - Instituto 24 - Android

    var BASE_UNEG = "ilcb"

    var ENV_DOMAIN = "demo" // dev, pre cuenta corriente
    //var ENV_DOMAIN = "production" //cuenta orient

    var RETURN_DOMAIN = "https://devecommerce"
    //var RETURN_DOMAIN = "https://preecommerce"
    //var RETURN_DOMAIN = "https://ecommerce"

    var RETURN_TRAMITE = "https://devalumno"
    //var RETURN_TRAMITE = "https://prealumno"
    //var RETURN_TRAMITE = "https://alumno"
}

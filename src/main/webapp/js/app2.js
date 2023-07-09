const form = document.getElementById("form");
form.addEventListener("submit", e => {
    e.preventDefault();

    var resultadocalculo = 0;
    var porcentaje = 0;
    const valorticket = 200;
    
    const nombre = document.getElementById("inputnombre");
    const apellido = document.getElementById("inputapellido");
    const correo = document.getElementById("inputcorreo");
    const cantidad = document.getElementById("inputcantidad");
    const estudiante = document.getElementById("inputEstudiante");
    var totalApagar = document.getElementById("totalApagar");

    

    function calculartotal ()
    {
    
        if (estudiante.value == "Estudiante...") {
            porcentaje = 80;
            
        } else if (estudiante.value == "Trainee...") {
            porcentaje = 50;
        }
         else {
            porcentaje = 15;
        }            
        resultadocalculo = (cantidad.value*valorticket) - (porcentaje*(cantidad.value*valorticket))/100;
        totalApagar.value="Total a Pagar: $ " + resultadocalculo;
    }
    
    if (nombre.value == "Nombre") {
        alert("Debe ingresar Nombre");         
    } else {    
        if (apellido.value == "Apellido") {
           alert("Debe ingresar Apellido");            
         } else {
                if (cantidad.value == 'cantidad' || isNaN(cantidad.value) == true) {
                    alert("Debe ingresar Cantidad");            
                }else{
                    calculartotal();                    
                     }              
                }     
            }
})     
function ClearForm(){
    document.getElementById('form').reset();                  
}

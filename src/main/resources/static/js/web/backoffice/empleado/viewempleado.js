$(document).on("click", "#btnnuevo", function(){
    $("#empleadoModalLabel").html("Nuevo Empleado");
    $("#hddempleadoid").val("0");
    $("#hddfoto").val("");
    $("#txtnomemp").val("");
    $("#txtapeemp").val("");
    $("#txtfecnac").val("");
    $("#txtemail").val("");
    $("#txttelefono").val("");
    $("#txtdireccion").val("");
    deshabilitarCampos(false, true);
    mostrarEstadoEmpleado(false, false);
    $("#modalempleado").modal("show");
})

$(document).on("click", ".btndetalles", function(){
    $("#empleadoModalLabel").html("Detalles Empleado");
    cargarModalEmpleado($(this).attr("data-empid"), true, true, true);
    $("#modalempleado").modal("show");
})

$(document).on("click", ".btneditar", function(){
    $("#empleadoModalLabel").html("Editar Empleado");
    cargarModalEmpleado($(this).attr("data-empid"), false, false, false);
    $("#modalempleado").modal("show");
})

function cargarModalEmpleado(empleadoid, deshabilitar, esconderSwitch, mostrarEstado) {
    $.ajax({
        type: "GET",
        url: "/empleado/buscar/" + empleadoid,
        dataType: "json",
        success: function(resultado) {
            $("#hddempleadoid").val(resultado.empleadoid);
            $("#txtnomemp").val(resultado.nomemp);
            $("#txtapeemp").val(resultado.apeemp);
            $("#txtfecnac").val(resultado.fecnac);
            $("#txtemail").val(resultado.email);
            $("#txttelefono").val(resultado.telefono);
            $("#hddfoto").val(resultado.foto);
            $("#txtdireccion").val(resultado.direccion);
            $("#switchestado").prop("checked", resultado.estado == true ? true : false);
            deshabilitarCampos(deshabilitar, esconderSwitch);
            mostrarEstadoEmpleado(mostrarEstado, resultado.estado);
        }
    })
}

function deshabilitarCampos(deshabilitar, esconderSwitch) {
    $("#txtnomemp").prop("readonly", deshabilitar);
    $("#txtapeemp").prop("readonly", deshabilitar);
    $("#txtfecnac").prop("readonly", deshabilitar);
    $("#txtemail").prop("readonly", deshabilitar);
    $("#txttelefono").prop("readonly", deshabilitar);
    $("#txtdireccion").prop("readonly", deshabilitar);
    $("#txtfoto").val("");
    esconderSwitch == true ? $("#divestado").hide() : $("#divestado").show();
    $("#switchestado").prop("disabled", deshabilitar);
    deshabilitar == true ? $("#divfoto").hide() : $("#divfoto").show();
    $("#cbodepartamento").prop("disabled", deshabilitar);
    $("#cboprovincia").prop("disabled", deshabilitar);
    $("#cbodistrito").prop("disabled", deshabilitar);
}

function mostrarEstadoEmpleado(mostrar, estado){
    if(mostrar == true) {
        if(estado == true) {
            $("#alertactivo").show();
            $("#alertinactivo").hide();
        } else {
            $("#alertactivo").hide();
            $("#alertinactivo").show();
        }
    } else {
        $("#alertactivo").hide();
        $("#alertinactivo").hide();
    }
}
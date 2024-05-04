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
    $("#switchestado").prop("checked", true);
    deshabilitarCampos(false, true);
    mostrarEstadoEmpleado(false, false);
    cargarCboDepartamento(0);
    resetearCbo("#cboprovincia");
    resetearCbo("#cbodistrito");
    $("#cboprovincia").prop("disabled", true);
    $("#cbodistrito").prop("disabled", true);
    $("#modalempleado").modal("show");
})

$(document).on("click", ".btndetalles", function(){
    $("#empleadoModalLabel").html("Detalles Empleado");
    cargarModalEmpleado($(this).attr("data-empid"), true, true, true, true);
    $("#modalempleado").modal("show");
})

$(document).on("click", ".btneditar", function(){
    $("#empleadoModalLabel").html("Editar Empleado");
    cargarModalEmpleado($(this).attr("data-empid"), false, false, false, false);
    $("#modalempleado").modal("show");
})

$(document).on("change", "#cbodepartamento", function(){
    if($("#cbodepartamento").val() == -1) {
        resetearCbo("#cboprovincia");
        resetearCbo("#cbodistrito");
        $("#cboprovincia").prop("disabled", true);
        $("#cbodistrito").prop("disabled", true);
    } else {
        cargarCboProvincia(0, $("#cbodepartamento").val());
        resetearCbo("#cbodistrito");
        $("#cboprovincia").prop("disabled", false);
        $("#cbodistrito").prop("disabled", true);
    }
})

$(document).on("change", "#cboprovincia", function(){
    if($("#cboprovincia").val() == -1) {
        resetearCbo("#cbodistrito");
        $("#cbodistrito").prop("disabled", true);
    } else {
        cargarCboDistrito(0, $("#cboprovincia").val());
        $("#cbodistrito").prop("disabled", false);
    }
})

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: $("#hddempleadoid") == 0 ? "POST" : "PUT",
        url: "/empleado/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            empleadoid: $("#hddempleadoid").val(),
            nomemp: $("#txtnomemp").val(),
            apeemp: $("#txtapeemp").val(),
            estado: $("#switchestado").prop("checked"),
            fecnac: $("#txtfecnac").val(),
            email: $("#txtemail").val(),
            telefono: $("#txttelefono").val(),
            foto: $("#hddfoto").val(),
            direccion: $("#txtdireccion").val(),
            distritoid: $("#cbodistrito").val()
        }),
        success: function(resultado) {
            alertaDeRespuesta("", resultado.mensaje, resultado.respuesta ? "success" : "error");
            if(resultado.respuesta) {
                listarEmpleados();
                $("#modalempleado").modal("hide");
            }
        }
    })
})

function cargarModalEmpleado(empleadoid, deshabilitar, esconderSwitch, mostrarEstado, soloLectura) {
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
            $("#txtdireccion").val(resultado.direccion != null ? resultado.direccion :
                                   soloLectura == true ? "No registrado" : "");
            $("#switchestado").prop("checked", resultado.estado == true ? true : false);
            deshabilitarCampos(deshabilitar, esconderSwitch);
            mostrarEstadoEmpleado(mostrarEstado, resultado.estado);
            cargarCboDepartamento(resultado.distrito.provincia.departamento.departamentoid);
            cargarCboProvincia(resultado.distrito.provincia.provinciaid,
                               resultado.distrito.provincia.departamento.departamentoid);
            cargarCboDistrito(resultado.distrito.distritoid, resultado.distrito.provincia.provinciaid);
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

function cargarCboDepartamento(departamentoid){
    $.ajax({
        type: "GET",
        url: "/departamento/lista",
        data: "json",
        success: function(resultado) {
            resetearCbo("#cbodepartamento");
            $.each(resultado, function(index, value){
                llenarCbo("#cbodepartamento", value.departamentoid, value.nomdepa);
            });
            cambiarValCbo("#cbodepartamento", departamentoid);
        }
    })
}

function cargarCboProvincia(provinciaid, departamentoid){
    $.ajax({
        type: "GET",
        url: "/provincia/lista/" + departamentoid,
        data: "json",
        success: function(resultado) {
            resetearCbo("#cboprovincia");
            $.each(resultado, function(index, value){
                llenarCbo("#cboprovincia", value.provinciaid, value.nomprov);
            });
            cambiarValCbo("#cboprovincia", provinciaid);
        }
    })
}

function cargarCboDistrito(distritoid, provinciaid){
    $.ajax({
        type: "GET",
        url: "/distrito/lista/" + provinciaid,
        data: "json",
        success: function(resultado) {
            resetearCbo("#cbodistrito");
            $.each(resultado, function(index, value){
                llenarCbo("#cbodistrito", value.distritoid, value.nomdist);
            });
            cambiarValCbo("#cbodistrito", distritoid);
        }
    })
}

function listarEmpleados(){
    $.ajax({
        type: "GET",
        url: "/empleado/lista",
        dataType: "json",
        success: function(resultado) {
            $("#tblempleado > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblempleado > tbody").append(
                    `<tr>` +
                        `<td class="text-center">${value.apeemp + ' ' + value.nomemp}</td>` +
                        `<td class="text-center">${value.email}</td>` +
                        `<td class="text-center">${value.telefono}</td>` +
                        `<td class="text-center">${value.estado == true ? "Activo" : "Inactivo"}</td>` +
                        `<td class="text-center">` +
                            `<button type="button" class="btn btn-success btndetalles" data-empid="${value.empleadoid}">` +
                                `<i class="bi bi-file-earmark-richtext"></i>` +
                            `</button>` +
                        `</td>` +
                        `<td class="text-center">` +
                            `<button type="button" class="btn btn-primary btneditar" data-empid="${value.empleadoid}">` +
                                `<i class="bi bi-pencil"></i>` +
                            `</button>` +
                        `</td>` +
                    `</tr>`
                );
            });
        }
    })
}

function resetearCbo(cbo){
    $(cbo).empty();
    $(cbo).append(
        `<option value="-1">Seleccionar opción</option>`
    );
}

function llenarCbo(cbo, value, name){
    $(cbo).append(
        `<option value="${value}">${name}</option>`
    );
}

function cambiarValCbo(cbo, val){
    if(val > 0)
        $(cbo).val(val);
    else
        $(cbo).val(-1);
}

function alertaDeRespuesta(_title, _text, _icon) {
    Swal.fire({
        title: _title,
        text: _text,
        icon: _icon,
        timer: 1500,
        showConfirmButton: false
    })
}
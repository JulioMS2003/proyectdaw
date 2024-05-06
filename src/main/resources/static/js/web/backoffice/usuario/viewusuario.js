var idroles = [];

$(document).on("click", "#btnnuevo", function(){
    $("#usuarioModalLabel").html("Nuevo Usuario")
    $("#hddusuarioid").val("0");
    desactivarCampos(false);
    $("#txtnomusuario").val("");
    $("#txtapeusuario").val("");
    $("#divultimologin").hide();
    $("#switchactivo").prop("checked", true);
    $("#divactivo").hide();
    $("#btnaceptar").hide();
    cargarRoles(false, []);
    mostrarAlertaEstado(false, false);
    mostrarBotones(false, true, false);
    cargarIdRoles(true, []);
    $("#modalusuario").modal("show");
})

$(document).on("click", ".btndetalles", function(){
    $("#divultimologin").show();
    cargarModalUsuario($(this).attr("data-usuarioid"), true, true, false, true, false, false, true, false, false);
})

$(document).on("click", ".btneditar", function() {
    $("#divultimologin").hide();
    cargarModalUsuario($(this).attr("data-usuarioid"), false, false, true, false, false, true, false, true, true);
})

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/usuario/guardar/nuevo",
        contentType: "application/json",
        data: JSON.stringify({
            nomusuario: $("#txtnomusuario").val(),
            apeusuario: $("#txtapeusuario").val(),
            idroles: idroles
        }),
        success: function(resultado) {
            if(resultado.respuesta) {
                listarUsuarios();
                alertaDeRespuesta(" ", resultado.mensaje, "success");
                $("#modalusuario").modal("hide");
            } else {
                alertaDeRespuesta(" ", resultado.mensaje, "error");
            }
        }
    })
})

$(document).on("click", "#btnactualizar", function(){
    $.ajax({
        type: "PUT",
        url: "/usuario/actualizar",
        contentType: "application/json",
        data:JSON.stringify({
            usuarioid: $("#hddusuarioid").val(),
            nomusuario: $("#txtnomusuario").val(),
            apeusuario: $("#txtapeusuario").val(),
            activo: $("#switchactivo").prop("checked"),
            idroles: idroles
        }),
        success: function(resultado) {
            alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success" : "error");
            if(resultado.respuesta) {
                listarUsuarios();
                $("#modalusuario").modal("hide");
            }
        }
    })
})

$(document).on("change", ".checkboxrol", function() {
    let rolid = $(this).val();
    if($(this).prop("checked")) {
        idroles.push(rolid);
    } else {
        idroles = idroles.filter(id => id != rolid);
    }
})

function cargarModalUsuario(usuarioid, mostrarEstado, desactivarC,
                            mostrarSwitch, mostrarBtnAceptar, mostrarBtnGuardar,
                            mostrarBtnActualizar, soloMostrarRoles, actualizando,
                            cargar) {
    $.ajax({
        type: "GET",
        url: "/usuario/" + usuarioid,
        dataType: "json",
        success: function(resultado) {
            $("#usuarioModalLabel").html("Usuario #" + resultado.username.substring(7, 11));
            desactivarCampos(desactivarC);
            $("#hddusuarioid").val(resultado.usuarioid);
            $("#txtnomusuario").val(resultado.nomusuario);
            $("#txtapeusuario").val(resultado.apeusuario);
            mostrarAlertaEstado(resultado.activo, mostrarEstado);
            $("#txtultimologin").val(resultado.ultimologin == null ? 'No registra' :
                                     moment(resultado.ultimologin).format('YYYY-MM-DD HH:mm:ss'));
            $("#divroles").html("");
            cargarIdRoles(cargar, resultado.roles);
            if(soloMostrarRoles)
                mostrarRoles(resultado.roles);
            else
                cargarRoles(actualizando, resultado.roles);
            mostrarSwitch ? $("#divactivo").show() : $("#divactivo").hide();
            $("#switchactivo").prop("checked", resultado.activo);
            mostrarBotones(mostrarBtnAceptar, mostrarBtnGuardar, mostrarBtnActualizar);
            $("#modalusuario").modal("show");
        }
    })
}

function cargarRoles(actualizando, roles){
    let rolesIds = roles.map(function(rol) {
        return rol.rolid;
    });

    $.ajax({
        type: "GET",
        url: "/rol/lista",
        dataType: "json",
        success: function(resultado) {
            $("#divroles").html("");
            $.each(resultado, function(index, value){
                if(value.nomrol != "Administrador") {
                    let checked = rolesIds.includes(value.rolid) && actualizando ? "checked" : "";
                    $("#divroles").append(
                        `<div class="form-check">` +
                            `<input class="form-check-input checkboxrol" type="checkbox" value="${value.rolid}" id="check${value.rolid}" ${checked}>` +
                            `<label class="form-check-label" for="check${value.rolid}">${value.nomrol}` +
                            `<label>` +
                        `</div>`
                    );
                }
            });
        }
    })
}

function mostrarRoles(roles) {
    $.each(roles, function(index, value) {
        $("#divroles").append(
            `<p>- ${value.nomrol}</p>`
        );
    })
}

function cargarIdRoles(cargar, roles){
    idroles = [];
    if(cargar) {
        $.each(roles, function(index, value) {
            idroles.push(value.rolid);
        });
    }
}

function listarUsuarios(){
    $.ajax({
        type: "GET",
        url: "/usuario/lista",
        dataType: "json",
        success: function(resultado) {
            $("#tblusuario > tbody").html("");
            $.each(resultado, function(index, value) {
                $("#tblusuario > tbody").append(
                    `<tr>` +
                        `<td>${value.username}</td>` +
                        `<td>${value.apeusuario + ' ' + value.nomusuario}</td>` +
                        `<td>${value.activo == true ? 'Activo' : 'Inactivo'}</td>` +
                        `<td>${value.ultimologin == null ? 'No registra' : moment(value.ultimologin).format('YYYY-MM-DD HH:mm:ss')}</td>` +
                        `<td class="text-center">` +
                            `<button type="button" class="btn btn-success btndetalles" ` +
                                     `data-usuarioid="${value.usuarioid}">` +
                                `<i class="bi bi-file-earmark-richtext"></i>` +
                            `</button>` +
                        `</td>` +
                        `<td class="text-center">` +
                            `<button type="button" class="btn btn-primary btneditar" ` +
                                     `data-usuarioid="${value.usuarioid}">` +
                                `<i class="bi bi-pencil"></i>` +
                            `</button>` +
                        `</td>` +
                    `</tr>`
                )
            })
        }
    })
}

function desactivarCampos(desactivar) {
    if(desactivar) {
        $("#txtnomusuario").prop("readonly", true);
        $("#txtapeusuario").prop("readonly", true);
    } else {
        $("#txtnomusuario").prop("readonly", false);
        $("#txtapeusuario").prop("readonly", false)
    }
}

function mostrarAlertaEstado(activo, mostrar){
    if(!mostrar) {
        $("#alertactivo").hide();
        $("#alertinactivo").hide();
    } else {
        if(activo) {
            $("#alertactivo").show();
            $("#alertinactivo").hide();
        } else {
            $("#alertactivo").hide();
            $("#alertinactivo").show();
        }
    }
}

function mostrarBotones(mostrarBtnAceptar, mostrarBtnGuardar, mostrarBtnActualizar) {
    mostrarBtnAceptar ? $("#btnaceptar").show() : $("#btnaceptar").hide();
    mostrarBtnGuardar ? $("#btnguardar").show() : $("#btnguardar").hide();
    mostrarBtnActualizar ? $("#btnactualizar").show() : $("#btnactualizar").hide();
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
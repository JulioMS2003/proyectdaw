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
    cargarRoles();
    mostrarAlertaEstado(false, false);
    $("#modalusuario").modal("show");
})

$(document).on("click", ".btndetalles", function(){
    $("#divultimologin").show();
    cargarModalUsuario($(this).attr("data-usuarioid"), true, true);
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

$(document).on("change", ".checkboxrol", function() {
    let rolid = $(this).val();
    if($(this).prop("checked")) {
        idroles.push(rolid);
    } else {
        idroles = idroles.filter(id => id !== rolid);
    }
})

function cargarModalUsuario(usuarioid, mostrarEstado, desactivar) {
    $.ajax({
        type: "GET",
        url: "/usuario/" + usuarioid,
        dataType: "json",
        success: function(resultado) {
            $("#usuarioModalLabel").html("Usuario #" + resultado.username.substring(7, 11));
            desactivarCampos(desactivar);
            $("#txtnomusuario").val(resultado.nomusuario);
            $("#txtapeusuario").val(resultado.apeusuario);
            mostrarAlertaEstado(resultado.activo, mostrarEstado);
            $("#txtultimologin").val(resultado.ultimologin == null ? 'No registra' :
                                     moment(resultado.ultimologin).format('YYYY-MM-DD HH:mm:ss'));
            $("#divroles").html("");
            $.each(resultado.roles, function(index, value) {
                $("#divroles").append(
                    `<p>- ${value.nomrol}</p>`
                );
            });
            $("#modalusuario").modal("show");
        }
    })
}

function cargarRoles(){
    $.ajax({
        type: "GET",
        url: "/rol/lista",
        dataType: "json",
        success: function(resultado) {
            $("#divroles").html("");
            $.each(resultado, function(index, value){
                if(value.nomrol != "Administrador") {
                    $("#divroles").append(
                        `<div class="form-check">` +
                            `<input class="form-check-input checkboxrol" type="checkbox" value="${value.rolid}" id="check${value.rolid}">` +
                            `<label class="form-check-label" for="check${value.rolid}">${value.nomrol}` +
                            `<label>` +
                        `</div>`
                    );
                }
            });
        }
    })
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

function alertaDeRespuesta(_title, _text, _icon) {
    Swal.fire({
        title: _title,
        text: _text,
        icon: _icon,
        timer: 1500,
        showConfirmButton: false
    })
}
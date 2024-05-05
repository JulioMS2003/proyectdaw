var idroles = [];

$(document).on("click", "#btnnuevo", function(){
    $("#usuarioModalLabel").html("Nuevo Usuario")
    $("#hddusuarioid").val("0");
    $("#txtnomusuario").val("");
    $("#txtapeusuario").val("");
    $("#switchactivo").prop("checked", true);
    $("#divactivo").hide();
    $("#btnaceptar").hide();
    cargarRoles();
    $("#modalusuario").modal("show");
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

function cargarRoles(){
    $.ajax({
        type: "GET",
        url: "/rol/lista",
        dataType: "json",
        success: function(resultado) {
            $("#divroles").html("");
            $("#divroles").append(
                `<hr />` +
                `<h5 class="text-center fw-bold">Seleccionar roles:</h5>`
            )
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

function alertaDeRespuesta(_title, _text, _icon) {
    Swal.fire({
        title: _title,
        text: _text,
        icon: _icon,
        timer: 1500,
        showConfirmButton: false
    })
}
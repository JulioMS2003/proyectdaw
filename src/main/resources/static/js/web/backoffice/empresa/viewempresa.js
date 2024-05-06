$(document).on("click", "#btnnuevo", function(){
    $("#empresaModalLabel").html("Nueva Empresa");
    $("#hddempresaid").val("0");
    $("#txtnomempresa").val("");
    $("#txtruc").val("");
    $("#switchactivo").prop("checked", true);
    $("#divestado").hide();
    $("#modalempresa").modal("show");
})

$(document).on("click", ".btneditar", function(){
    $("#empresaModalLabel").html("Editar Empresa");
    $("#hddempresaid").val($(this).attr("data-empid"));
    $("#txtnomempresa").val($(this).attr("data-empnom"));
    $("#txtruc").val($(this).attr("data-empruc"));
    $("#switchactivo").prop("checked", $(this).attr("data-empact") ? true : false);
    $("#divestado").show();
    $("#modalempresa").modal("show");
})

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: $("#hddempresaid").val() == 0 ? "POST": "PUT",
        url: "/empresa/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            empresaid: $("#hddempresaid").val(),
            nomempresa: $("#txtnomempresa").val(),
            ruc: $("#txtruc").val(),
            activo: $("#switchactivo").prop("checked")
        }),
        success: function(resultado) {
            alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success": "error");
            if(resultado.respuesta) {
                listarEmpresas();
                $("#modalempresa").modal("hide");
            }
        }
    })
})

function listarEmpresas(){
    $.ajax({
        type: "GET",
        url: "/empresa/lista",
        dataType: "json",
        success: function(resultado) {
            $("#tblempresa > tbody").html("");
            $.each(resultado, function(index, value) {
                $("#tblempresa > tbody").append(
                    `<tr>` +
                        `<td>${value.nomempresa}</td>` +
                        `<td>${value.ruc}</td>` +
                        `<td>${value.activo ? 'Activa' : 'Inactiva'}</td>` +
                        `<td class="text-center">` +
                            `<button type="button" class="btn btn-primary btneditar" ` +
                                     `data-empid="${value.empresaid}" data-empnom="${value.nomempresa}" ` +
                                     `data-empruc="${value.ruc}" data-empact="${value.activo}">` +
                                `<i class="bi bi-pencil"></i>` +
                            `</button>` +
                        `</td>` +
                    `</tr>`
                )
            })
        }
    })
}

function alertaDeRespuesta(_title, _text, _icon){
    Swal.fire({
        title: _title,
        text: _text,
        icon: _icon,
        timer: 1500,
        showConfirmButton: false
    })
}
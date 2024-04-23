$(document).on("click", "#btnagregar", function(){
    $("#txtidusuario").val("");
    $("#txtclaveusuario").val("");
    $("#txtnomusuario").val("");
    $("#txtapeusuario").val("");
    $("#cborol").val("");
    $("#cboestado").val("");
    $("#hddusuario").val("0")
    listarCboRol(0);
    $("#switchusuario").hide();
    $("#cboestado").prop("checked", true);
    $("#modalusuario").modal("show");
});
$(document).on("click", ".btnactualizar", function(){
    $("#txtidusuario").val($(this).attr("data-usuarioid"));
    $("#txtclaveusuario").val($(this).attr("data-usuarioclave"));
    $("#txtnomusuario").val($(this).attr("data-usuarionom"));
    $("#txtapeusuario").val($(this).attr("data-usuarioape"));
    $("cborol").empty();
    listarCboRol($(this).attr("data-usuariorol"));
    $("#switchusuario").show();
    $("#modalusuario").modal("show");
});

function listarCboRol(idRol) {
    $.ajax({
        type: "GET",
        url: "/rol/get",
        dataType: "json",
        success: function (resultado) {
            $.each(resultado, function (index, value) {
                $("#cborol").append(
                    `<option value="${value.rolid}">${value.nomrol}</option>`
                )
            });
            if (idRol > 0) {
                $("#cborol").val(idRol);
            }
        }
    });
}
jQuery(document).ready(function () {
    // Form Principal
    var $form = $('#registroMercanciaFilter');
    $form.on('submit', function (e) {
        e.preventDefault();
        // boton pulsado
        var action = $(document.activeElement).val();
        if(action == 'find'){

            $.ajax({
                url: $form.attr('action'), type: 'post', data: $form.serialize(),
                success: function (data) {
                    $("#formRegistro").html(data);
                }
            });
        }else{

            $.ajax({
                url: '/registromercancia/new', type: 'get',
                success: function (data) {
                    $("#editModalHolder").html(data);
                    $('#editModal').modal({backdrop: 'static'});
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    swal("Mensaje del sistema!", XMLHttpRequest.responseJSON.result, "error");
                }
            });
        }
    });

    var $formRegistro = $('#formRegistro');
    $formRegistro.on('submit', function (e) {
        e.preventDefault();
        // boton pulsado
        var action = $(document.activeElement).val();

            $.ajax({
                url: $formRegistro.attr('action'), type: 'put', data: $formRegistro.serialize(),
                success: function (data) {
                    swal("Mensaje del sistema!", data.result, "success");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    swal("Mensaje del sistema!", XMLHttpRequest.responseJSON.result, "error");
                }
            });
    });

    modalEventsActions();
});

function modalEventsActions() {

    // Evento que se ejecuta cuando se abre la ventana
    $(document).on('show.bs.modal', '#editModal', function () {
        commonModalActions();
    });

    $(document).on('hide.bs.modal', '#editModal', function (e) {

    });
}

function commonModalActions() {
// Form Modal
    var $formModal = $('#userModalForm');
    $formModal.on('submit', function (e) {
        e.preventDefault();
        // boton pulsado
        var action = $(document.activeElement).val();
        $.ajax({
            url: $formModal.attr('action'),type:'post', data: $formModal.serialize(),
            success: function (response) {
                $('#editModal').toggleClass('programmatic');
                $('#editModal').modal('hide');
                var mensaje=" Numero de guia: "+response.guia+" Valor Envio: "+response.valor;

                swal("Mensaje del sistema!", mensaje, "success");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                swal("Mensaje del sistema!", XMLHttpRequest.responseJSON.result, "error");
            }
        });
    });
}
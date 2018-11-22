$(document).ready(function () {
    $('#datatable1').DataTable({
        "pagingType": "full_numbers",
        "baseStyle": "stripe",
        "aLengthMenu": [[5, 10, 20], [5, 10, 20]],
        "iDisplayLength" : 10,
        "aaSorting": [ 1, "desc" ]
    });

});
$(document).ready(function () {

    $('#route-table').DataTable();

    $('.detail').click(function (e) {

        e.preventDefault();
        let href = $(this).attr('href');

        $.getJSON(href, function (response) {
            let html = '<tbody>';
            for (let map of response) {
                html += `<tr><td>${map.depPoint}</td>` +
                    `<td>${map.arrPoint}</td>` +
                    `<td>${map.distance}</td>` +
                    `<td>${map.transport.name}</td>` +
                    `<td>${map.transport.speed}</td>` +
                    `<td>${map.transport.maxWeight}</td>` +
                    `<td>${map.transport.costPerKm}</td></tr>`
            }
            html += '</tbody>';
            $('.routeForm #point-table').append(html);
        });
        $('#routeFormModal').modal();
    });

    $('#routeFormModal').on('hide.bs.modal', function() {
        $('#point-table tbody').remove();
    });

    $('.a-radio').click(function(e) {
        e.preventDefault();


    });

});


$(function () {
    $('.course-tabs .timeline li ').on('click',function () {
   var panelToShow   =  $(this).attr('rel');
   var panels =  $('.course-tabs .timeline li ');
   console.log(panels);
   for (var i = 0; i<panels.length;i++){
       if($(panels[i]).attr("rel")!= $(this).attr('rel')){
          $( panels[i]).removeClass('active');
       }
   }
   console.log(this);
   $(this).addClass('active');

   $('.course-table').hide(function () {
$(this).removeClass('active');
      $('#'+ panelToShow).show(200,function () {
          $(this).addClass('active');

      });
   });

    })
})

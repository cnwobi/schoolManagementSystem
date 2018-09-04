$(function () {
    $('.course-tabs .timeline li ').on('click',function () {
   var panelToShow   =  $(this).attr('rel');
   var panels =  $('.course-tabs .timeline li ');

   /*iterate through the panels and remove active*/
   for (var i = 0; i<panels.length;i++){
       if($(panels[i]).attr("rel")!= $(this).attr('rel')){
          $( panels[i]).removeClass('active');
       }
   }
   console.log(this);
   /*Add the active class to the currently clicked li*/
   $(this).addClass('active');
   /*remove the active class from all tables*/
   $('.course-table').removeClass('active');

   /*Add active to the panel you wish to show*/
   $('#'+ panelToShow).addClass('active');

    })
})

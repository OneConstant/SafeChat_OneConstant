// Hamburger Js
function hamburger() {
  $("#menu-btn").click(function () {
    // Toggle Mobile Menu
    $("#menu-btn").toggleClass("open");
    // $('#menu').toggleClass('flex');
    $("#menu").toggleClass("hidden");
  });
}

$(document).ready(function () {
  hamburger();
});

// Regex for Email Validation
// $(document).ready(function () {
//     $('#invalid-email').hide();
//   $("#email").change(function () {
//     var inputvalues = $(this).val();
//     var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
//     if (!regex.test(inputvalues) || $('#email').val().length() === 0)  {
//       $('#invalid-email').toggleClass("flex");
//       return regex.test(inputvalues);
//     }
//   });
// });

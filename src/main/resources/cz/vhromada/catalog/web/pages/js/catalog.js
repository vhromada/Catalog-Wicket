$(document).ready(function () {
  imdbShow();
});

var imdbShow = function () {
  var checked = $('#imdb:checked').length > 0;
  if (checked) {
    $('#imdbPanel').show();
  } else {
    $('#imdbPanel').hide();
  }
};



  document.addEventListener('DOMContentLoaded', function() {
    var strength = {
      "0": "This password is as weak as a peeking panda.",
      "1": "This password is as weak as a young panda cub.",
      "2": "This password is as stealthy as a panda ninja.",
      "3": "This password is as strong as a bamboo-guarding panda.",
      "4": "This password is as mighty as a powerful panda."
    }
    
    
	var color = {
		"0":"color: #FF0000;",
		"1":"color: #964B00 ;",
		"2":"color: #FF8C00 ;",
		"3":"color: #006400 ;",
		"4":"color: #008000 ;"
	}

    var password = document.getElementById('password');
/*    var meter = document.getElementById('password-strength-meter');
*/    var text = document.getElementById('password-strength-text');

    password.addEventListener('input', function() {
      var val = password.value;
      var result = zxcvbn(val);

      // Update the password strength meter
/*      meter.value = result.score;
*/
      // Update the text indicator
      col = color[result.score]
      console.log(col);
      if (val !== "") {
		text.innerHTML = '<span style="'+col+'">'+strength[result.score]+'</span>';
      } else {
        text.innerHTML = "Panda awaits a secret pawshake. Choose wisely, protect fiercely.";
      }
    });
  });


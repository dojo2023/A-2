//PWの確認
      function pushHideButton() {
        var txtPass = document.getElementById("user_pw");
        var btnEye = document.getElementById("pw_check_button");
        if (txtPass.type === "text") {
          txtPass.type = "password";
          btnEye.className = "fa fa-eye-slash";
        } else {
          txtPass.type = "text";
          btnEye.className = "fa fa-eye";
        }
      }

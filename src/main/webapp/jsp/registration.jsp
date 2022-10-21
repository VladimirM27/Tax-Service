<%--
  Created by IntelliJ IDEA.
  User: vovam
  Date: 21.10.2022
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registration</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
  <link href="../styles/registration.css" rel="stylesheet">
  <script type="text/javascript" src="CheckPassword.js"></script>
</head>
<body>

<section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Registration Form</h3>
            <form  action="/Tax-service/controller" method="post"  class ="needs-validation" onsubmit="submitForm" novalidate>
              <input id="POST-command" type="hidden" name="command" value="Registration" >

              <div class="row">
                <div class="col-md-6 mb-4">
                  <div class="form-outline">
                    <input type="text" id="firstName" class="form-control form-control-lg" name="firstName" />
                    <label class="form-label" for="firstName">First Name</label>
                  </div>
                </div>

                <div class="col-md-6 mb-4">
                  <div class="form-outline">
                    <input type="text" id="lastName" class="form-control form-control-lg" name="lastName"/>
                    <label class="form-label" for="lastName">Last Name</label>
                  </div>
                </div>

              </div>

              <div class="row">
                <div class="form-outline  mb-4">
                  <input type="email" id="emailAddress" class="form-control form-control-lg" name="emailAddress"/>
                  <label class="form-label" for="emailAddress">Email</label>
                </div>
              </div>

              <div class="row">
                <div class="form-outline mb-4">
                  <input type="password" id="firstPassword" class="form-control form-control-lg" />
                  <label class="form-label" for="form3Example4cg">Password</label>
                </div>

                <div class="form-outline mb-4">
                  <input type="password" id="secondPasswod" class="form-control form-control-lg" name="secondPassword"/>
                  <label class="form-label" for="form3Example4cdg">Repeat your password</label>
                  <div class="invalid-feedback">
                    Passwords do not match
                  </div>
                </div>
              </div>



              <div class="row">
                <div class="form-outline mb-4">
                  <select class="form-select" name="entity">
                    <option value="1" disabled>Choose entity</option>
                    <option value="individual">individual</option>
                    <option value="legal">legal</option>
                  </select>
                  <label class="form-label select-label">Choose entity</label>
                </div>
              </div>




              <div class="mt-4 pt-2">
                <input class="btn btn-primary btn-lg" type="submit" value="Submit" />
              </div>

            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

</body>
</html>

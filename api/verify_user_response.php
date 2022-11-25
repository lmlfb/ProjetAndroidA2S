<?php

//error_reporting(E_ALL);

try {
function utf8ize($d) {
  if (is_array($d)) 
      foreach ($d as $k => $v) 
          $d[$k] = utf8ize($v);

   else if(is_object($d))
      foreach ($d as $k => $v) 
          $d->$k = utf8ize($v);

   else 
      return utf8_encode($d);

  return $d;
}

$servername = "apiapkslm.mysql.db";
$username = "apiapkslm";
$password = "MWcYrxMarR9vdU9NGmf";
$dbname = "apiapkslm";

$id_question = -1;
$user_response = "";




if (isset($_GET["id_question"])&&isset($_GET["user_response"])) {
  $id_question = $_GET["id_question"];
  $user_response = $_GET["user_response"];
}
else{
  echo("please provide values");
}

$base  = new mysqli($servername, $username, $password, $dbname);


//---------------------RETRIEVE CURRENT QUESTION TO GET CORRECT RESPONSE----------------------------------

$stmt = $base->prepare("SELECT * FROM enonce WHERE id=?");
$stmt->bind_param("i", $id_question);
$stmt->execute();
$result = $stmt->get_result();
$return_arr = array();

while ($row = mysqli_fetch_assoc($result)) {

$row_array['reponse'] = $row['reponse'];

array_push($return_arr,$row_array);
}

echo $row_array['reponse'];

$stmt->close();
//--------------------------------------------------------------------------------------------------------

//---------------------RETRIEVE CURRENT QUESTION TO GET CORRECT RESPONSE----------------------------------

$stmt2 = $base->prepare($row_array['reponse']);
$stmt2->execute();
$result2 = $stmt2->get_result();
$return_arr2 = array();

while ($row2 = mysqli_fetch_assoc($result2)) {

//$correctionResult['reponse'] = $row2['reponse'];

$correctionResult['id'] = $row2['id'];
$correctionResult['model'] = $row2['model'];
$correctionResult['prix'] = $row2['prix'];
$correctionResult['id_option'] = $row2['id_option'];
$correctionResult['kilometrage'] = $row2['kilometrage'];

array_push($return_arr2,$correctionResult);

echo json_encode(utf8ize($return_arr2));
}


$stmt2->close();


//Execute on user table

//---------------------RETRIEVE CURRENT QUESTION TO GET CORRECT RESPONSE----------------------------------


//---------------------EXECUTE USER SUBMIT----------------------------------

echo "\n\n";
echo $_GET['user_response'];
echo "\n\n";

$stmt3 = $base->prepare($_GET["user_response"]);
$stmt3->execute();
$result3 = $stmt3->get_result();
$return_arr3 = array();

while ($row3 = mysqli_fetch_assoc($result3)) {

//$correctionResult['reponse'] = $row2['reponse'];

$userResult['id'] = $row3['id'];
$userResult['model'] = $row3['model'];
$userResult['prix'] = $row3['prix'];
$userResult['id_option'] = $row3['id_option'];
$userResult['kilometrage'] = $row3['kilometrage'];

array_push($return_arr3,$userResult);

//echo json_encode(utf8ize($return_arr2));
}

$stmt3->close();

//--------------------------------------------------------------------------

if (count(array_diff(array_merge($correctionResult, $userResult), array_intersect($correctionResult, $userResult))) === 0) {
  echo "same";
}
else{
  echo "not done";
}

/*
$stmt = $base->prepare("SELECT * FROM voiture WHERE modele IS Porsche;");
$stmt->execute();
$result = $stmt->get_result();
$return_arr = array();

while ($row = mysqli_fetch_assoc($result)) {

$row_array['id'] = $row['id'];
$row_array['model'] = $row['model'];
$row_array['prix'] = $row['prix'];
$row_array['id_option'] = $row['id_option'];
$row_array['kilometrage'] = $row['kilometrage'];


array_push($return_arr,$row_array);
}*/





//-------------------------------------------------------------------------------------------------------


mysqli_close($base);

} catch (mysqli_sql_exception $e) {
  echo "MySQLi Error Code: " . $e->getCode() . "<br />";
  echo "Exception Msg: " . $e->getMessage();
  exit(); 
}


/*
try {
  // Try Connect to the DB with new MySqli object - Params {hostname, userid, password, dbname}
  $mysqli = new mysqli($servername, $username, "", $dbname);

  
  $statement = $mysqli->prepare("select * from enonce");


  $statement->execute(); // Execute the statement.
  $result = $statement->get_result(); // Binds the last executed statement as a result.

  echo json_encode(($result->fetch_assoc())); // Parse to JSON and print.

} catch (mysqli_sql_exception $e) { // Failed to connect? Lets see the exception details..
  echo "MySQLi Error Code: " . $e->getCode() . "<br />";
  echo "Exception Msg: " . $e->getMessage();
  exit(); // exit and close connection.
}

$mysqli->close(); */

?>
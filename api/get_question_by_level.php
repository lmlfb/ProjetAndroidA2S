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
/*
$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "androidapk";

$mysqli  = new mysqli($servername, $username, $password, $dbname);


$selected_level = $_GET["lvl"];

$selected_level = 1;
$stmt = $mysqli->prepare("SELECT * FROM enonce WHERE exoLvl=?");
$stmt->bind_param("i", $selected_level);
$result = $stmt->execute();*/

//---------------

$lvl = -1;
if (isset($_GET["lvl"])) {
  $lvl = $_GET["lvl"];
}
else{
  echo("please provide a value for lvl");
}


$base  = new mysqli($servername, $username, $password, $dbname);
$stmt = $base->prepare("SELECT * FROM enonce WHERE exoLvl=?");
$stmt->bind_param("i", $lvl);
$stmt->execute();
$result = $stmt->get_result();

$return_arr = array();




    while ($row = mysqli_fetch_assoc($result)) {
      $row_array['id'] = $row['id'];
      $row_array['titre'] = $row['titre'];
      $row_array['question'] = $row['question'];
      $row_array['array'] = $row['array'];
      $row_array['exoNb'] = $row['exoNb'];
      $row_array['exoLvl'] = $row['exoLvl'];

    array_push($return_arr,$row_array);
   }
 
 echo json_encode(utf8ize($return_arr));
mysqli_close($base);

} catch (mysqli_sql_exception $e) { // Failed to connect? Lets see the exception details..
  echo "MySQLi Error Code: " . $e->getCode() . "<br />";
  echo "Exception Msg: " . $e->getMessage();
  exit(); // exit and close connection.
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
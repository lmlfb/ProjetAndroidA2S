<?php

//error_reporting(E_ALL);


$login = "default";
$mdp = "default";
if (isset($_POST["login"]) && isset($_POST["mdp"]) ) {
  $login = $_POST["login"];
  $mdp = $_POST["mdp"];


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

$base  = new mysqli($servername, $username, $password, $dbname);
$stmt = $base->prepare("SELECT COUNT(id) AS DoesUserExist FROM users WHERE login=?");
$stmt->bind_param("s", $login);
$stmt->execute();
$result = $stmt->get_result();
//print_r($result);
//echo("hello");
$DoesUserExist = 0;

    while ($row = mysqli_fetch_assoc($result)) {
      $DoesUserExist = $row['DoesUserExist'];
   }
 
if($DoesUserExist == 0){

  $stmt = $base->prepare("INSERT INTO users (login, mdp) VALUES (?, ?)");
  $stmt->bind_param("ss", $login, $mdp);
  $stmt->execute();
  
}

$DoesUserExist == 1 ? $UserCreated = 0 : $UserCreated = 1;


$res = array(
  "IsUserCreated" => $UserCreated,
);

$fin = array();
array_push($fin,$res);



echo json_encode(utf8ize($fin));

mysqli_close($base);


} catch (mysqli_sql_exception $e) { // Failed to connect? Lets see the exception details..
  echo "MySQLi Error Code: " . $e->getCode() . "<br />";
  echo "Exception Msg: " . $e->getMessage();
  exit(); // exit and close connection.
}


}
else{
  
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
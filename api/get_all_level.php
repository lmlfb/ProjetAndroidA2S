<?php
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
$sql = "select * from lvl";
$return_arr = array();



if ($result = mysqli_query( $base, $sql )){
    while ($row = mysqli_fetch_assoc($result)) {
    $row_array['exoLvl'] = $row['exoLvl'];
    $row_array['titre'] = $row['titre'];

    array_push($return_arr,$row_array);
   }
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
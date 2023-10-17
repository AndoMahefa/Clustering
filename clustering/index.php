<?php 
    require('connect.php');
    include('MySessionHandler.php');
    $db = new mysqli('localhost', 'root', 'root', 'handler');
    $handler = new MySessionHandler($db);
    session_set_save_handler($handler, true);
    session_start();

?>
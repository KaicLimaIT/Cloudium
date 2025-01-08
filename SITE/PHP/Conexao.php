<?php

$Bco ='m8416_Cloudium';
$Usuario  ='m8416_TCC';
$Senha = 'MainSett07';

try 
{
    $Conexao = new PDO("mysql:host=mysql8.serv00.com; dbname=$Bco", "$Usuario", "$Senha");
    $Conexao->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);   
    $Conexao->exec("set names utf8");    
}
catch (PDOException $erro)
{
    echo "Erro na conexão" . $erro->getMessage();
    
}
?>

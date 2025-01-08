<?php

session_start();
  
include "Conexao.php";

    $Botao = $_POST["botaoLogin"];
    $Email = $_POST["emailLogin"];
    $Senha = $_POST["senhaLogin"];
 
    if($Botao = "Entrar"){
        try{
            $Comando=$Conexao->prepare("SELECT * FROM TB_USUARIO WHERE EMAIL_USUARIO=? AND SENHA_USUARIO = ?");

        $Comando->bindParam(1, $Email);
        $Comando->bindParam(2, $Senha);
        
            if($Comando->execute()){
                if($Comando->rowCount() > 0){
                    while($Linha = $Comando->fetch(PDO::FETCH_OBJ))
                    {
                        setcookie('user_logged_in', '1', 0, '/', '', true, true);

                        $Id = $Linha->ID_USUARIO;
                        $_SESSION['Id_Usuario'] = $Id;

                        $Usuario = $Linha->USER_USUARIO;
                        $_SESSION['Usuario'] = $Usuario;

                        $Email = $Linha->EMAIL_USUARIO;
                        $_SESSION['Email'] = $Email;

                        header('Location: ../index.php');
                    }
                }
                else
                {
                    echo "<script> 
                      alert('Email e/ou senha não confere!')
                      </script>";
                      header('Location ../login_signup.html');
                  
                }
            }
        }
        catch(PDOException $erro){
            echo "Erro no Login: " . $erro->getMessage();
        }
    }
    else{
        echo "<script> alert('Email e/ou senha não confere!')</script>";

        $Email = null;
        $Senha = null;
    }

?>

<?php
    session_start();
    include "Conexao.php";

    if($Botao == "Cadastrar"){
        try{

            $Usuario= $_POST["userCad"];
            $Email = $_POST["emailCad"];
            $Senha = $_POST["senhaCad"];

            if($_POST["confSenha"] == $_POST["senhaCad"]){
                $Comando = $Conexao->prepare("INSERT INTO TB_USUARIO (USER_USUARIO, EMAIL_USUARIO, SENHA_USUARIO) VALUES
                 (?, ?, ?)");
                $Comando->bindParam(1, $Usuario);
                $Comando->bindParam(2, $Email);
                $Comando->bindParam(3, $Senha);
                
                if($Comando->execute()){
                    if($Comando->rowCount()){

                        $Email = null;
                        $Senha = null;
                        
                        echo "<script> alert('Cadastro Realizado com Sucesso!');
                                       window.location='../login_signup.html';</script>";

                        
                    }
                    else{
                        echo "Erro ao tentar a conexão";
                    }
                }
                else{
                    throw new PDOException("Erro: não foi possível realizar a Query");
                }
            }
            else{
                echo "Senhas não conferem!";
            }
        }    
        catch(PDOException $erro){
            echo "Erro no Cadastro " . $erro->getMessage();
        }
    }
?>

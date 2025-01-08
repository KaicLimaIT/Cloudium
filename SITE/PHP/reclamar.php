<?php
    $Botao = $_POST['botaoReclama'];
    $Titulo = $_POST['complaintTitle'];
    $Conteudo = $_POST['complaintContent'];
    $Id_Usuario = $_SESSION['Id_Usuario'];
    $Data = date('d/m/Y');

    session_start();
    include "Conexao.php";

    if($Botao == "Enviar Reclamação"){
        try{
            $Comando = $Conexao->prepare("INSERT INTO TB_RECLAMACOES(ID_USUARIO, TITULO_REC, DESC_REC, DTA_REC, STATUS_REC) 
                                         VALUES (?,?,?,?,'Aberto')");
            $Comando->bindParam(1, $Id_Usuario);
            $Comando->bindParam(2, $Titulo);
            $Comando->bindParam(3, $Conteudo);
            $Comando->bindParam(4, $Data);

            if($Comando->execute()){
                if($Comando->rowCount() > 0){
                    $Titulo = null;
                    $Conteudo = null;

                    echo "<script> 
                          alert('Foi') 
                          window.location.href='../reclamar.php';
                          </script>";
                }
                else{
                    echo "Erro ao tentar a conexão";
                }
            }
            else{
                throw new PDOException("Erro: não foi possível realizar a Query");
            }
        }
        catch(PDOException $erro){
            echo "Erro no Cadastro " . $erro->getMessage();
        }
    }
?>
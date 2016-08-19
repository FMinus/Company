var myApp = angular.module("myApp",[]);
myApp.controller("InscriptionController",function($http,$scope)
{
    $scope.test="myTest";
    $scope.etudiant = {};
    $scope.errors = null;
    $scope.mode={value:"form"};
    $scope.exception = {message:null};
    
    $scope.saveEtudiant = function()
    {
        $http.post("saveEtudiant",$scope.etudiant)
        .success(function(data)
        {
            if(!data.errors)
            {
                $scope.etudiant = data;
                $scope.errors = null;
                $scope.mode.value = "confirm";
            }
            else
            {
                //$scope.etudiant = null;
                $scope.errors = data;
            }
            
        })
        .error(function(data)
        {
            $scope.exception.message = data.message;
        });
        
    };
});

myApp.controller("IndexController", function($http, $scope)
{
    $scope.menu = ["Inscrire","Listes","Utilisateurs","Logout"];
    $scope.selectedMenu = null;
    
    $scope.select = function(m)
    {
        $scope.selectedMenu = m;
        console.log("click : "+$scope.selectedMenu);
    };
    
});

myApp.controller("ListEtudiantsController",function($http,$scope)
{
    $scope.test="myTest";
    $scope.pageEtudiants = null;
    $scope.pageCourante = 0;
    $scope.size = 5;
    
    $scope.listEtudiants = function()
    {
        $http.get("etudiants?page="+$scope.pageCourante+"&size="+$scope.size)
                .success(function(data)
                {
                    $scope.pageEtudiants = data;
                });
    };
    
    $scope.listEtudiants();
});

# Terraform

## Setup Terraform

### MacOS

First, install the HashiCorp tap, a repository of all our Homebrew packages.
 ```
 $ brew tap hashicorp/tap$ brew tap hashicorp/tap
 ```
Now, install Terraform with hashicorp/tap/terraform

```
$ brew install hashicorp/tap/terraform
```

To update to the latest version of Terraform, first update Homebrew.

```
$ brew update
```
 
 Then, run the upgrade command to download and use the latest Terraform version.

 ```
 $ brew upgrade hashicorp/tap/terraform
 ```

 To verify Terraform installation

 ```
 $ terraform -help
 ```

 Add any subcommand to terraform -help to learn more about what it does and available options.

 ```
 $ terraform -help plan
 ```

 To enable auto complete (Bash shell)
 ```
 $ touch ~/.bashrc
 $ terraform -install-autocomplete
 ```

 ### Linux

 https://learn.hashicorp.com/tutorials/terraform/install-cli

 ```
 $ sudo apt-get update && sudo apt-get install -y gnupg software-properties-common curl
 ```

Add the HashiCorp GPG key.
```
$ curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo apt-key add -
```

Add the official HashiCorp Linux repository.
```
$  sudo apt-add-repository "deb [arch=amd64] https://apt.releases.hashicorp.com $(lsb_release -cs) main"
```

Update to add the repository, and install the Terraform CLI.
```
$ sudo apt-get update && sudo apt-get install terraform
```

 ### Windows (using choclatey)

 ```
 $ choco install terraform
 ```

 ## Test Teraform Locally

Change Directory to examples directory

```
$ cd examples/1_local-setup-mac-linux
```

If you are on Mac Start Docker Desktop
```
$ open -a Docker
```

Initialize the Terraform
```
$ terraform init
```

Apply Terraform IaC

```
$ terraform apply
```

Test the 1st Terraform IaC, Write the following URL in your browser

http://localhost:8000 

You will see the following Data in the browser

![Terraform local setup](https://raw.githubusercontent.com/MetaArivu/terraform-eks/master/images/terraform-local-server-ex.png)

If you can see Welcome to NGINX on your browser - then Congrats - Your 1st IaC is working!!!

(c) Copyright MetaArivu. Apache 2.0 License 



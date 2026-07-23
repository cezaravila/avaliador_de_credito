.DEFAULT_GOAL := create

pre:
	@kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/v0.15.3/config/manifests/metallb-native.yaml	
	@kubectl wait --namespace metallb-system \
		--for=condition=ready pod \
		--selector=app=metallb \
		--timeout=300s
	@kubectl apply -f manifests/
	
helm:
	@helmfile apply

create:
	@kind create cluster --config config.yaml

up: create pre helm

destroy:
	@kind delete clusters kind

stop_container:
	@docker stop $(shell docker ps -q)

start_container:
	@docker start kind-control-plane kind-worker2 kind-worker

# Quando tem o Python instalado
passwd_python:
	@echo "JENKINS":
	@kubectl get secret -n jenkins jenkins -o jsonpath="{.data.jenkins-admin-password}" | python -m base64 -d

# Quando não tem o Python instalado e preciso
# jogar o base64 que sair e um site que decodifica para mim
passwd_without_python:
	@echo "JENKINS":
	@kubectl get secret -n jenkins jenkins -o jsonpath="{.data.jenkins-admin-password}"
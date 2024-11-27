#!make

start-redis:
	@echo "===== Starting docker containers ====="
	@docker-compose -f docker-compose.yml up -d redis

stop-redis:
	@echo "===== Stopping docker containers ====="
	@docker stop redis
	@docker rm redis

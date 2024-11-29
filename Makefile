#!make

start-redis:
	@echo "===== Starting docker containers ====="
	@docker-compose -f docker-compose.yml up -d redis-labseq

stop-redis:
	@echo "===== Stopping docker containers ====="
	@docker stop redis-labseq
	@docker rm redis-labseq

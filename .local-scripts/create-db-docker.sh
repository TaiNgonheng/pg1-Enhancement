db_docker_container_name="spring-pg"
db_docker_container_name_result=$( docker ps -a -q -f name="$db_docker_container_name" )

if [[ -n "$db_docker_container_name_result" ]]; then
	  echo "#### Container $db_docker_container_name_result already exists, removing..."
	  docker rm -f "$db_docker_container_name"
fi
docker run -d \
  --name "$db_docker_container_name" \
  -p 5433:5432 \
  -e POSTGRES_USER=spring_user \
  -e POSTGRES_PASSWORD=dummy \
  -e POSTGRES_DB=spring_pg_db \
  postgres:10

#  -v ~/custom/mount/document-template-db:/var/lib/postgresql/data \

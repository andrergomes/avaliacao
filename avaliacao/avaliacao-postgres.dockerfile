 version: '3'

 services:
  avaliacao-postgres:
    image: postgres 
    environment:
      POSTGRES_USER: "andre"
      POSTGRES_PASSWORD: "andre@postgres"
      POSTGRES_DB: "avaliacao"
    ports:
      - "15432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data     
    networks:
      - postgres-compose-network
      
  avaliacao-pgadmin:
    image: dpage/pgadmin4
    environment: 
      PGADMIN_DEFAULT_EMAIL: "mamute@gmail.com"     
      PGADMIN_DEFAULT_PASSWORD: "postgres"
    ports:
      - "16543:80"
    depends_on:
      - avaliacao-postgres
    networks:
      - postgres-compose-network
 volumes:
  pgdata:
    driver: local
 networks: 
  postgres-compose-network:
    driver: bridge
Example:

POST http://localhost:8081/api/questions
{
  "text": "What is your favorite color?",
  "answers": ["black", "red", "blue"],
  "type": "POLL"
}

GET http://localhost:8081/api/questions/1

POST http://localhost:8081/api/questions/1/vote
{
  "answer": "black"
}

# 📄 Document QA

A simple AI-powered web application that allows users to **upload a PDF and ask questions about the document**.

The application extracts text from the PDF and uses **Spring AI with OpenRouter** to generate an answer based on the document content.

## 🚀 Features

- Upload a PDF document
- Extract text from the PDF
- Ask questions about the uploaded document
- Get AI-generated answers
- Simple React user interface
- Spring Boot REST API
- Docker support
- Deployed using Render

## 🛠️ Technologies Used

### Backend
- Java 21
- Spring Boot
- Spring AI
- Maven
- Apache PDFBox

### Frontend
- React
- Vite
- JavaScript
- CSS

### AI
- OpenRouter API
- Spring AI

### Deployment
- Docker
- Nginx
- Render

## 📁 Project Structure

```text
Document_QA/
│
├── Dockerfile
│
├── document-qa/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/document_qa/
│           │       ├── config/
│           │       ├── controller/
│           │       ├── dto/
│           │       └── service/
│           │
│           └── resources/
│
└── frontend/
    ├── package.json
    ├── src/
    └── public/
```

## 🔄 How It Works

```text
User
 ↓
React Frontend
 ↓
Upload PDF
 ↓
Spring Boot
 ↓
PDFBox extracts text
 ↓
User asks a question
 ↓
Spring AI
 ↓
OpenRouter
 ↓
AI Answer
 ↓
React displays the answer
```

## 🔌 Main API Endpoints

### Upload PDF

```http
POST /api/documents/upload
```

Uploads a PDF and extracts its text.

### Ask Question

```http
POST /api/questions
```

Sends a question to the backend and returns an AI-generated answer.

## 🔑 Environment Variable

The OpenRouter API key is required to use the AI functionality.

Set it as:

```text
OPENROUTER_API_KEY=your_api_key
```

**Do not add your real API key to GitHub.**

The API key should be stored using environment variables.

## 💻 Run Locally

### 1. Clone the project

```bash
git clone https://github.com/Ramana0306/Document_QA.git
```

```bash
cd Document_QA
```

### 2. Set the API key

PowerShell:

```powershell
$env:OPENROUTER_API_KEY="your_api_key"
```

### 3. Start the backend

```bash
mvn -f document-qa/pom.xml spring-boot:run
```

The backend runs on:

```text
http://localhost:8080
```

### 4. Start the frontend

Open another terminal:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the frontend:

```bash
npm run dev
```

## 🐳 Run Using Docker

Build the Docker image:

```bash
docker build -t document-qa-app .
```

Run the application:

```bash
docker run -d \
  --name document-qa-app \
  -p 80:80 \
  -e "OPENROUTER_API_KEY=your_api_key" \
  document-qa-app
```

Then open:

```text
http://localhost
```

## ☁️ Deployment

The application is deployed using **Docker on Render**.

Live application:

https://document-qa-4c0m.onrender.com

The API key is stored as an environment variable in Render and is not included in the source code.

## 🔐 Security

Sensitive files and information are excluded from GitHub using `.gitignore`.

The API key is provided through an environment variable instead of being written directly in the source code.

## 📚 What I Learned

Through this project, I practiced:

- Building REST APIs using Spring Boot
- Handling file uploads
- Extracting PDF text using PDFBox
- Using Spring AI
- Calling an AI API
- Connecting React with Spring Boot
- Using environment variables
- Creating a Docker image
- Using Nginx as a reverse proxy
- Deploying a Docker application

## 🔮 Future Improvements

- Support more document formats
- Allow multiple documents
- Add chat history
- Improve document search
- Add user authentication
- Add RAG with vector database

## 👨‍💻 Author

**Ramana M**

B.Tech Information Technology

GitHub:  
https://github.com/Ramana0306

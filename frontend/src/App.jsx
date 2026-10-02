import { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [file, setFile] = useState(null);
  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const uploadDocument = async () => {
    if (!file) {
      setMessage("Please select a PDF first.");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      setLoading(true);
      setMessage("");

      const response = await axios.post(
          "/api/documents/upload",
          formData
      );

      setMessage(response.data);
    } catch (error) {
      setMessage("Failed to upload document.");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const askQuestion = async () => {
    if (!question.trim()) {
      setMessage("Please enter a question.");
      return;
    }

    try {
      setLoading(true);
      setAnswer("");
      setMessage("");

      const response = await axios.post(
          "/api/questions",
          {
            question: question,
          }
      );

      setAnswer(response.data);
    } catch (error) {
      setMessage("Failed to get answer.");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
      <div className="container">
        <h1>📄 Document Q&A</h1>

        <p className="subtitle">
          Upload a PDF and ask questions about it using AI.
        </p>

        <div className="card">
          <h2>Upload Document</h2>

          <input
              type="file"
              accept=".pdf"
              onChange={(e) => setFile(e.target.files[0])}
          />

          <button onClick={uploadDocument} disabled={loading}>
            {loading ? "Uploading..." : "Upload PDF"}
          </button>

          {message && <p className="message">{message}</p>}
        </div>

        <div className="card">
          <h2>Ask a Question</h2>

          <textarea
              placeholder="Example: What is this document about?"
              value={question}
              onChange={(e) => setQuestion(e.target.value)}
          />

          <button onClick={askQuestion} disabled={loading}>
            {loading ? "Thinking..." : "Ask AI"}
          </button>
        </div>

        {answer && (
            <div className="card answer">
              <h2>AI Answer</h2>
              <p>{answer}</p>
            </div>
        )}
      </div>
  );
}

export default App;
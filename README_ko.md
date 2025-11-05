# 🌊 Flowlog - Visual Task Flow Management System
> *Track your work. Visualize your flow.*

**Flowlog**은 팀과 개인이 작업의 흐름을 한눈에 파악하고,  
효율적으로 프로젝트를 관리할 수 있도록 돕는 **Kanban 스타일의 업무 관리 시스템**입니다.  
JIRA, Asana, Trello 등의 핵심 개념을 기반으로,  
**Spring Boot (Java)**와 **React (Next.js + TypeScript)**로 설계된 풀스택 프로젝트입니다.

---

## 🚀 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Frontend** | React (Next.js 14), TypeScript, TailwindCSS, Zustand, dnd-kit, React Query |
| **Backend** | Spring Boot 3, Java 17, Spring Security (JWT), JPA, MySQL |
| **Infrastructure** | Docker, Docker Compose, GitHub Actions, Swagger UI |
| **Testing** | JUnit5, Mockito, React Testing Library |
| **Deployment** | Vercel (Frontend), Render / AWS ECS (Backend) |

---

## 🎯 Features (Planned)

- ✅ **User Authentication** – Sign Up / Login / JWT Token 인증  
- ✅ **Project Management** – 프로젝트 생성 / 수정 / 삭제  
- ✅ **Kanban Board** – 드래그 앤 드롭으로 Task 상태 이동  
- ✅ **Task Management** – 담당자, 우선순위, 마감일 지정  
- ✅ **Dashboard** – 진행 상태 통계 차트 (Recharts 기반)  
- 🚧 **Comment & File Attachments** – 향후 확장 예정  
- 🚧 **Team Collaboration** – 다중 사용자 협업 기능  

---

## 🧠 System Architecture

```
[Frontend]  React (Next.js + TypeScript)
       ↓  REST API
[Backend]  Spring Boot (Controller → Service → Repository)
       ↓
[Database] MySQL (Docker container)
```

---

## 🗂️ Project Structure (Planned)

```
flowlog/
 ┣ backend/
 ┃ ┣ src/main/java/com/flowlog
 ┃ ┣ src/main/resources
 ┃ ┗ build.gradle
 ┣ frontend/
 ┃ ┣ app/
 ┃ ┣ components/
 ┃ ┣ store/
 ┃ ┗ lib/
 ┣ docs/
 ┃ ┣ 01_Requirements.md
 ┃ ┣ 02_SystemDesign.md
 ┃ ┗ 03_ScreenDesign.md
 ┗ README.md
```

---

## ⚙️ Getting Started (Local Setup)

### 1️⃣ Clone Repository
```bash
git clone https://github.com/jangsu-kim/flowlog.git
cd flowlog
```

### 2️⃣ Backend Setup
```bash
cd backend
./gradlew bootRun
```

### 3️⃣ Frontend Setup
```bash
cd ../frontend
npm install
npm run dev
```

### 4️⃣ Access
```
Frontend: http://localhost:3000
Backend:  http://localhost:8080
```

---

## 📅 Roadmap

| Phase | Target | Status |
|--------|---------|--------|
| ✅ Phase 1 | Repository setup & README 작성 | Done |
| 🚧 Phase 2 | Requirements Definition & ERD 설계 | In Progress |
| ⏳ Phase 3 | Backend API (Auth / Project / Task) 구현 | Planned |
| ⏳ Phase 4 | Frontend Integration (Kanban UI) | Planned |
| ⏳ Phase 5 | Deployment & Docs 완성 | Planned |

---

## 👤 Author

**KIM JANGSU (김장수)**  
Backend Engineer / Fullstack Developer  
📍 Osaka, Japan  
📧 jangsoo719@gmail.com  
🔗 [GitHub Profile](https://github.com/JangsuKim)

---

## 📜 License

This project is licensed under the [MIT License](./LICENSE).


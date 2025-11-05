# 🌊 Flowlog - Visual Task Flow Management System
> *Track your work. Visualize your flow.*

**Flowlog（フローログ）** は、チームや個人が作業の流れを一目で把握し、  
効率的にプロジェクトを管理できる **カンバン方式のタスク管理システム** です。  
JIRA、Asana、Trello の主要な概念をベースに、  
**Spring Boot (Java)** と **React (Next.js + TypeScript)** によって設計されたフルスタックプロジェクトです。

---

## 🚀 技術スタック

| 層 | 使用技術 |
|----|------------|
| **フロントエンド** | React (Next.js 14), TypeScript, TailwindCSS, Zustand, dnd-kit, React Query |
| **バックエンド** | Spring Boot 3, Java 17, Spring Security (JWT), JPA, MySQL |
| **インフラ** | Docker, Docker Compose, GitHub Actions, Swagger UI |
| **テスト** | JUnit5, Mockito, React Testing Library |
| **デプロイ** | Vercel (フロントエンド), Render / AWS ECS (バックエンド) |

---

## 🎯 主な機能（予定）

- ✅ **ユーザー認証** – 新規登録／ログイン／JWT トークン認証  
- ✅ **プロジェクト管理** – プロジェクトの作成／編集／削除  
- ✅ **カンバンボード** – タスクをドラッグ＆ドロップで移動  
- ✅ **タスク管理** – 担当者、優先度、期限の設定  
- ✅ **ダッシュボード** – 進捗状況の統計グラフ（Rechartsベース）  
- 🚧 **コメント・ファイル添付** – 今後追加予定  
- 🚧 **チームコラボレーション** – 複数ユーザーによる共同作業機能  

---

## 🧠 システム構成

```
[Frontend]  React (Next.js + TypeScript)
       ↓  REST API
[Backend]  Spring Boot (Controller → Service → Repository)
       ↓
[Database] MySQL (Docker container)
```

---

## 🗂️ プロジェクト構成（予定）

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

## ⚙️ 開発環境のセットアップ（ローカル）

### 1️⃣ リポジトリのクローン
```bash
git clone https://github.com/jangsu-kim/flowlog.git
cd flowlog
```

### 2️⃣ バックエンドの起動
```bash
cd backend
./gradlew bootRun
```

### 3️⃣ フロントエンドの起動
```bash
cd ../frontend
npm install
npm run dev
```

### 4️⃣ アクセスURL
```
Frontend: http://localhost:3000
Backend:  http://localhost:8080
```

---

## 👤 作者

**KIM JANGSU（キム・ジャンス）**  
バックエンドエンジニア／フルスタックデベロッパー  
📍 大阪府大阪市  
📧 jangsoo719@gmail.com  
🔗 [GitHub プロフィール](https://github.com/JangsuKim)

---

## 📜 ライセンス

本プロジェクトは [MIT ライセンス](./LICENSE) の下で公開されています。


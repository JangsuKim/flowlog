-- 기본 유저 추가
INSERT INTO users (email, password, name, role)
VALUES
  ('admin@flowlog.com', '1234', 'Admin', 'ADMIN'),
  ('user@flowlog.com', '1234', 'Jangsoo', 'USER');

-- 기본 프로젝트 추가
INSERT INTO projects (name, description, owner_id)
VALUES
  ('Flowlog 초기 프로젝트', '첫 번째 테스트 프로젝트', 1);

-- 기본 보드 컬럼 추가
INSERT INTO board_columns (project_id, name, position)
VALUES
  (1, '할 일', 1),
  (1, '진행 중', 2),
  (1, '완료', 3);

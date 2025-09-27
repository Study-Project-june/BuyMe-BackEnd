-- 1) 최신순 목록 (DESC 명시는 선택: 역방향 스캔 가능)
CREATE INDEX IF NOT EXISTS idx_store_created_at_desc
    ON store (created_at DESC);

-- 2) 카테고리 + 최신순
CREATE INDEX IF NOT EXISTS idx_store_category_created_desc
    ON store (category, created_at DESC);

-- 3) 이름 존재 확인 (정책에 따라 unique 여부 결정)
-- 중복 금지면:
CREATE INDEX IF NOT EXISTS idx_store_name ON store (name);
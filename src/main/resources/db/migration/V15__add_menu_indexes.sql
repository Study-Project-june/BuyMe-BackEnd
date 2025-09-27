-- ========== MENU ==========
-- 가게 상세에서 메뉴 목록 조회
CREATE INDEX IF NOT EXISTS idx_menu_store
    ON public.menu (store_id);

-- 정렬을 name
CREATE INDEX IF NOT EXISTS idx_menu_store_name
    ON public.menu (store_id, name);

-- 가격순 정렬을 자주 쓰면 주석 해제
CREATE INDEX IF NOT EXISTS idx_menu_store_price
    ON public.menu (store_id, price);

-- ========== MENU_OPTION ==========
-- 메뉴 상세에서 옵션 목록 조회
CREATE INDEX IF NOT EXISTS idx_menu_option_menu
    ON public.menu_option (menu_id);

--  옵션명을 정렬
CREATE INDEX IF NOT EXISTS idx_menu_option_menu_name
    ON public.menu_option (menu_id, name);
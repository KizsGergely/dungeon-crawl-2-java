DROP TABLE IF EXISTS public.game_state;
CREATE TABLE public.game_state (
    id serial NOT NULL PRIMARY KEY,
    current_map text NOT NULL,
    other_map text NOT NULL,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    player_id integer NOT NULL,
    save_name text NOT NULL
);

DROP TABLE IF EXISTS public.player;
CREATE TABLE public.player (
    id serial NOT NULL PRIMARY KEY,
    player_name text NOT NULL,
    hp integer NOT NULL,
    attack integer NOT NULL,
    defense integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    isCatFed boolean NOT NULL,
    isGrassCut boolean NOT NULL,
    grassToCut boolean NOT NULL,
    isWifeHappy boolean NOT NULL,
    onLevel integer NOT NULL
);

DROP TABLE IF EXISTS public.monster;
CREATE TABLE public.monster (
   id serial NOT NULL PRIMARY KEY,
   save_id integer NOT NULL,
   hp integer NOT NULL,
   attack integer NOT NULL,
   defense integer NOT NULL,
   x integer NOT NULL,
   y integer NOT NULL
);

--todo: write sql table for every monster
--wife:
--cat:

DROP TABLE IF EXISTS public.inventory;
CREATE TABLE public.inventory (
    id serial NOT NULL PRIMARY KEY,
    player_id integer NOT NULL,
    item_name text NOT NULL,
    item_count integer NOT NULL
);

ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);
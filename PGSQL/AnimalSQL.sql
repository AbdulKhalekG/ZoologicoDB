PGDMP                         y            Animal    12.3    12.3     �
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                        0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16406    Animal    DATABASE     �   CREATE DATABASE "Animal" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE "Animal";
                postgres    false            �            1259    16410    animal    TABLE     �   CREATE TABLE public.animal (
    idanimal integer NOT NULL,
    animal character varying(20),
    tipo character varying(20),
    comportamiento character varying(20)
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �
          0    16410    animal 
   TABLE DATA           H   COPY public.animal (idanimal, animal, tipo, comportamiento) FROM stdin;
    public          postgres    false    202   r       �
   �   x�}Q��0������ǈR�@E��%�ne��+7���Đ0��|w�5ܩ��KÁ{�} �%�Y�o(=����Z�5��0��Y0���s(h����S�*��:�{l����g�kV[8��&U7�\a(�0��(�D�ְ�pt�_�z	gL��Wp�1�3�z��qȦD9\)|!��y̕Ro,l�f     
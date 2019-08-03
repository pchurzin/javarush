package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        if (gameObject == null) {
            return false;
        }

        switch (direction) {
            case UP:
                return x == gameObject.x && y - Model.FIELD_CELL_SIZE == gameObject.y;
            case DOWN:
                return x == gameObject.x && y + Model.FIELD_CELL_SIZE == gameObject.y;

            case LEFT:
                return y == gameObject.y && x - Model.FIELD_CELL_SIZE == gameObject.x;

            case RIGHT:
                return y == gameObject.y && x + Model.FIELD_CELL_SIZE == gameObject.x;
        }
        return false;
    }

/*
    public static void main(String[] args) {
        CollisionObject co1 = new CollisionObject(0, 0) {
            @Override
            public void draw(Graphics graphics) {

            }
        };

        GameObject go = new GameObject(0, Model.FIELD_CELL_SIZE) {
            @Override
            public void draw(Graphics graphics) {

            }
        };

        System.out.println(co1.isCollision(go, Direction.UP));
        System.out.println(co1.isCollision(go, Direction.DOWN));
        System.out.println(co1.isCollision(go, Direction.LEFT));
        System.out.println(co1.isCollision(go, Direction.RIGHT));

        go.y = Model.FIELD_CELL_SIZE - 1;
        System.out.println(co1.isCollision(go, Direction.UP));
        System.out.println(co1.isCollision(go, Direction.DOWN));
        System.out.println(co1.isCollision(go, Direction.LEFT));
        System.out.println(co1.isCollision(go, Direction.RIGHT));


        System.out.println(co1.isCollision(co1, Direction.UP));
        System.out.println(co1.isCollision(co1, Direction.DOWN));
        System.out.println(co1.isCollision(co1, Direction.LEFT));
        System.out.println(co1.isCollision(co1, Direction.RIGHT));
    }
*/
}
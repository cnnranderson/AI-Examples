package com.cnnranderson.ai.util;

import com.badlogic.gdx.physics.box2d.*;

import static com.cnnranderson.ai.util.B2DConstants.PPM;

public class B2DBodyBuilder {

    Body mBody;
    BodyDef mBodyDef;
    FixtureDef mFixtureDef;
    Shape mShape;

    public B2DBodyBuilder() {
        mBodyDef = new BodyDef();
        mFixtureDef = new FixtureDef();

        initDefaultBodyDef();
        initDefaultShapeDef();
        initDefaultFixtureDef();
    }

    private void initDefaultBodyDef() {
        mBodyDef.type = BodyDef.BodyType.StaticBody;

        mBodyDef.position.set(0f, 0f);
        mBodyDef.fixedRotation = true;
        mBodyDef.linearDamping = 7.0f;
    }

    private void initDefaultShapeDef() {
        PolygonShape box = new PolygonShape();
        box.setAsBox(10 / PPM, 10 / PPM);
        mShape = box;
    }

    private void initDefaultFixtureDef() {
        mFixtureDef.shape = mShape;
        mFixtureDef.density = 1.0f;
    }

    public Body build(World world) {
        mBody = world.createBody(mBodyDef);
        mBody.createFixture(mFixtureDef);
        mShape.dispose();
        return mBody;
    }

    public B2DBodyBuilder staticBody() {
        mBodyDef.type = BodyDef.BodyType.StaticBody;
        return this;
    }

    public B2DBodyBuilder dynamicBody() {
        mBodyDef.type = BodyDef.BodyType.DynamicBody;
        return this;
    }

    public B2DBodyBuilder fixedRotation(boolean fixed) {
        mBodyDef.fixedRotation = fixed;
        return this;
    }

    public B2DBodyBuilder friction(float friction) {
        mFixtureDef.friction = friction;
        return this;
    }

    public B2DBodyBuilder density(float density) {
        mFixtureDef.density = density;
        return this;
    }

    public B2DBodyBuilder circleBody(float radius) {
        CircleShape shape = new CircleShape();
        shape.setRadius(radius / PPM);
        mShape = shape;
        return this;
    }

    public B2DBodyBuilder boxBody(float width, float height) {
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2 / PPM, height / 2 / PPM);
        mShape = box;
        return this;
    }

    public B2DBodyBuilder position(float x, float y) {
        mBodyDef.position.set(x, y);
        return this;
    }

    public B2DBodyBuilder x(float x) {
        mBodyDef.position.x = x;
        return this;
    }

    public B2DBodyBuilder y(float y) {
        mBodyDef.position.y = y;
        return this;
    }
}

package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.components.Position;
import za.co.sourlemon.zambies.ems.nodes.ZambieAINode;
import za.co.sourlemon.zambies.ems.nodes.ZambieAttractorNode;
import static java.lang.Math.*;
import static za.co.sourlemon.zambies.Utils.*;
import za.co.sourlemon.zambies.ems.AbstractSystem;

/**
 *
 * @author daniel
 */
public class ZambieAISystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<ZambieAINode> zambies = engine.getNodeList(ZambieAINode.class);
        List<ZambieAttractorNode> lunches = engine.getNodeList(ZambieAttractorNode.class);

        for (ZambieAINode zambie : zambies)
        {
            Position zamPos = zambie.position;

            if (lunches.isEmpty())
            {
                // TODO: make zambies "wander" when there is nothing to attract them
                zambie.velocity.vx = 0;
                zambie.velocity.vy = 0;
                continue;
            }

            float leastSq = Float.POSITIVE_INFINITY;
            float vecX = 0, vecY = 0;
            for (ZambieAttractorNode lunch : lunches)
            {
                Position lunchPos = lunch.position;
                float tempVecX = lunchPos.x - zamPos.x;
                float tempVecY = lunchPos.y - zamPos.y;
                float lunchSq = tempVecX * tempVecX + tempVecY * tempVecY;
                if (lunchSq < leastSq)
                {
                    leastSq = lunchSq;
                    vecX = tempVecX;
                    vecY = tempVecY;
                }
            }
            float lunchInvSqrt = invSqrt(leastSq);
            zambie.position.theta = (float) atan2(vecY, vecX);
            zambie.velocity.vx = (vecX * lunchInvSqrt) * zambie.ai.speed;
            zambie.velocity.vy = (vecY * lunchInvSqrt) * zambie.ai.speed;
        }
    }

    @Override
    public void end()
    {
    }
}

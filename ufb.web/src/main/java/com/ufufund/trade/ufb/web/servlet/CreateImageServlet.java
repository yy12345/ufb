// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2008-12-8 9:25:35
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   BuildImageServlet.java

package com.ufufund.trade.ufb.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CreateImageServlet extends HttpServlet{
	private static final Logger log = LoggerFactory.getLogger(CreateImageServlet.class);

    private Color getRandColor(int fc, int bc)
    {
        Random random = new Random();
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public CreateImageServlet()
    {
        generator = new Random();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        long beginTime = System.currentTimeMillis();
        int width = 55;//100;
        int height = 25;//40;
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("\u5B8B\u4F53", 0, 13));
        g.setColor(Color.WHITE);
        String sRand = "";
        for(int i = 0; i < 4; i++)
        {
            String rand = "";
            if(i % 2 == 0)
            {
                rand = getValidRand("N", 10, random);
                g.setFont(new Font(rand, 2, height-5));
            } else
            {
                g.setFont(new Font(rand, 0, height-5));
                rand = getValidRand("C", 10, random);
            }
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(String.valueOf(rand), 12 * i + 2, height - random.nextInt(5));
            sRand = sRand + rand;
        }

        for(int i = 0; i < 20; i++)
        {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }

        log.debug("created verifyCode="+sRand);
        
        HttpSession session = request.getSession();
        session.setAttribute("VerifyCode", sRand);
        g.dispose();
        long endTime = System.currentTimeMillis();
        beginTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
        beginTime = System.currentTimeMillis();
        ServletOutputStream out = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image);
        out.close();
        endTime = System.currentTimeMillis();
    }

    private void shear(Graphics g, int w1, int h1, Color color)
    {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    public void shearX(Graphics g, int w1, int h1, Color color)
    {
        int period = generator.nextInt(2);
        boolean borderGap = true;
        int frames = 1;
        int phase = generator.nextInt(2);
        for(int i = 0; i < h1; i++)
        {
            double d = (double)(period >> 1) * Math.sin((double)i / (double)period + (6.2831853071795862D * (double)phase) / (double)frames);
            g.copyArea(0, i, w1, 1, (int)d, 0);
            if(borderGap)
            {
                g.setColor(color);
                g.drawLine((int)d, i, 0, i);
                g.drawLine((int)d + w1, i, w1, i);
            }
        }

    }

    public void shearY(Graphics g, int w1, int h1, Color color)
    {
        int period = generator.nextInt(40) + 10;
        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for(int i = 0; i < w1; i++)
        {
            double d = (double)(period >> 1) * Math.sin((double)i / (double)period + (6.2831853071795862D * (double)phase) / (double)frames);
            g.copyArea(i, 0, 1, h1, 0, (int)d);
            if(borderGap)
            {
                g.setColor(color);
                g.drawLine(i, (int)d, i, 0);
                g.drawLine(i, (int)d + h1, i, h1);
            }
        }

    }

    public String getValidRand(String charType, int count, Random random)
    {
        String invalidCString = "01oOiIl";
        String rand = "";
        if("N".equalsIgnoreCase(charType))
        {
            for(int i = 0; i < count; i++)
            {
                rand = String.valueOf(random.nextInt(10));
                if(rand != null && invalidCString.indexOf(rand) == -1)
                    return rand;
            }

            return "9";
        }
        for(int i = 0; i < count; i++)
        {
            char c = 'A';
            c += random.nextInt(26);
            rand = String.valueOf(c);
            if(rand != null && invalidCString.indexOf(rand) == -1)
                return rand;
        }

        return "Q";
    }

    private Random generator;
}